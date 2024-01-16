package game.combatants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.CombatantAttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.player.PlayerManager;
import game.resethandling.Resettable;
import game.statuses.StatusEffect;
import game.statuses.StatusManager;
import game.utils.Status;
import game.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Combatant abstract class
 *
 * @author Behnam Mozafari, Gunnraj Dhaliwal, Josh ryan
 * @version 1.0
 */
public abstract class Combatant extends Actor implements Resettable {

  /**
   * Hashmap to hold Combatant behaviours
   */
  private final Map<Integer, Behaviour> behaviours = new TreeMap<>();
  /**
   * Boolean to determine whether instance needs to be reset
   */
  protected boolean toBeReset;
  /**
   * Boolean to determine whether combatant has a follow behaviour
   */
  private boolean following = false;

  /**
   * Chance of being despawned on any turn when not following player
   */
  private final int despawnChance;
  /**
   * True if combatant is an enemy to the player
   */
  private boolean enemy;

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Combatant(String name, char displayChar, int hitPoints, int despawnChance, boolean enemy) {
    super(name, displayChar, hitPoints);
    this.enemy = enemy;
    this.behaviours.put(999, new WanderBehaviour());
    this.addCapability(Status.HOSTILE_TO_ENEMY);
    this.despawnChance = despawnChance;
  }

  /**
   * Adds combatant attack behaviour to behaviour list, uses a weapon if one is provided.
   *
   * @param weapon weapon for attack behaviour.
   */
  protected void addCombatantAttackBehaviour(WeaponItem weapon) {
    if (weapon == null) {
      this.behaviours.put(1, new CombatantAttackBehaviour());
    } else {
      this.behaviours.put(1, new CombatantAttackBehaviour(weapon));
    }
  }

  private void addFollowBehaviour(GameMap map) {
    Location currLocation = map.locationOf(this);
    List<Exit> exits = currLocation.getExits();
    // keys 2 to 9 are reserved for follow behaviour in enemies
    int key = 2;
    for (Exit exit : exits) {
      Location exitLocation = exit.getDestination();
      Actor possibleTarget = exitLocation.getActor();
      if (possibleTarget!= null && possibleTarget.hasCapability(Status.FOLLOWED_BY_ENEMY)) {
        this.behaviours.put(key, new FollowBehaviour(possibleTarget));
        this.following = true;
        key ++;
      }
    }
  }

  /**
   * At each turn, select a valid action to perform.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in
   *                   conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the valid action that can be performed in that iteration or null if no valid action is
   * found
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    StatusManager statusManager = StatusManager.getInstance();
    List<StatusEffect> statuses = statusManager.getStatuses(this);
    List<StatusEffect> statusesTemp = new ArrayList<StatusEffect>();
    if (statuses != null && statuses.size() > 0) {
      for (StatusEffect status : new ArrayList<StatusEffect>(statuses)) {
        new Display().println(status.execute());
        if (status.getTurns() >= 0) {
          statusesTemp.add(status);
        }
      }
      statusManager.setStatuses(this, statusesTemp);
    }
    if (!this.following && enemy) {
      this.addFollowBehaviour(map);
    }
    if (toBeReset || (Utils.percentageCheck(despawnChance) && (!this.following))) {
      performReset(map);
      return new DoNothingAction();
    }
    if (this.hasCapability(Status.IMMOBILE)) {
      return new DoNothingAction();
    }
    for (Behaviour behaviour : behaviours.values()) {
      Action action = behaviour.getAction(this, map);
      if (action != null) {
        return action;
      }
    }
    return new DoNothingAction();
  }


  /**
   * Combatants can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return possible actions that can be done to the enemy
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    // stops friendly combatants from being attacked by player or other friendly combatants
    if (!this.enemy && otherActor.hasCapability(CombatantType.FRIENDLY)) {
      return actions;
    }
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      // intrinisic weapon attack
      actions.add(new AttackAction(this, direction));
      // weapon attack
      for (WeaponItem weapon : otherActor.getWeaponInventory()) {
        actions.add(new AttackAction(this, direction, weapon)); // adds attack action for weapon
        actions.add(weapon.getSkill(this, direction)); // adds special attack for weapon
      }
    }
    return actions;
  }

  /**
   * Called when combatant is to be reset
   */
  @Override
  public void reset() {
    toBeReset = true;
  }

  /**
   * resets combatant
   *
   * @param map the map containing the Combatant
   */
  public void performReset(GameMap map) {
    map.removeActor(this);
  }
}
