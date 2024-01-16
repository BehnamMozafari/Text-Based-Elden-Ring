package game.combatants.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.combatants.CombatantType;
import game.combatants.DropsRunes;
import game.resethandling.Resettable;
import game.utils.Status;

/**
 * Pile of BonesClass
 * @author Behnam Mozafari
 */
public class PileOfBones extends Actor implements DropsRunes, Resettable {

  /**
   * Counter to check if 3 turns have passed
   */
  private int turns = 0;
  /**
   * Boolean to determine whether instance needs to be reset
   */
  protected boolean toBeReset;
  /**
   * Instance of skeleton to respawn
   */
  private final Actor actorToRespawn;

  /**
   * Constructor.
   */
  public PileOfBones(Actor actorToRespawn) {
    super("Pile of Bones", 'X', 1);
    this.addCapability(CombatantType.PILEOFBONES);
    runeGenerator(this, 35, 892);
    this.actorToRespawn = actorToRespawn;
  }

  /**
   * Calls the spawnEnemy method after the PileOfBones has existed for three turns.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in
   *                   conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the Action to be performed
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    if (toBeReset) {
      map.removeActor(this);
      return new DoNothingAction();
    }
    turns += 1;
    if (turns == 3) {
      this.spawnSkeleton(map);
    }
    return new DoNothingAction();
  }

  /**
   * Enemies can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return possible actions that can be done to the enemy
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      actions.add(new AttackAction(this, direction));
      // HINT 1: The AttackAction above allows you to attack the enemy with your intrinsic weapon.
      // HINT 1: How would you attack the enemy with a weapon?
      for (WeaponItem weapon : otherActor.getWeaponInventory()) {
        actions.add(new AttackAction(this, direction, weapon)); // adds attack action for weapon
        actions.add(weapon.getSkill(this, direction)); // adds special attack for weapon
      }
    }
    return actions;
  }

  /**
   * Spawn the skeleton enemy, removes pile of bones.
   *
   * @param map containing actor
   */
  public void spawnSkeleton(GameMap map) {
    Location location = map.locationOf(this);
    map.removeActor(this);
    actorToRespawn.heal(Integer.MAX_VALUE);
    location.addActor(actorToRespawn);
  }

  /**
   * Called when enemy is to be reset
   */
  @Override
  public void reset() {
    toBeReset = true;
  }
}
