package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import java.util.List;

/**
 * An Action to perform an AOE attack.
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class AOEAttackAction extends Action {

  /**
   * Weapon used for the attack
   */
  private final Weapon weapon;

  /**
   * Constructor.
   *
   * @param weapon the weapon used to attack
   */
  public AOEAttackAction(Weapon weapon) {
    this.weapon = weapon;
  }

  /**
   * When executed, the chance to hit of the weapon that the Actor used is computed to determine
   * whether the actor will hit each target in its surrounding area. If so, deal damage to each
   * target and determine whether any targets are killed.
   *
   * @param actor The actor performing the attack action.
   * @param map   The map the actor is on.
   * @return the result of the attack, e.g. whether the target is killed, etc.
   * @see DeathAction
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    Location currLocation = map.locationOf(actor);
    List<Exit> exits = currLocation.getExits();
    String result = "";

    // Get all targets, create AttackAction for each target
    for (Exit exit : exits) {
      Actor actorAtExit = exit.getDestination().getActor();
      if (actorAtExit != null) {
        result += new AttackAction(actorAtExit, exit.getName(), weapon).execute(actor, map) + " ";
      }
    }

    return result;
  }

  /**
   * Describes which weapon the actor is doing the AOE attack with
   *
   * @param actor The actor performing the action.
   * @return the text we put on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " attacks all creatures in their surrounding area with " + weapon;
  }
}
