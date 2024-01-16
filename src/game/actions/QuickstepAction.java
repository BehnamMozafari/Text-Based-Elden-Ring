package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import java.util.Random;

/**
 * An Action to attack another Actor using the quickstep special ability.
 *
 * @author Josh Ryan
 */
public class QuickstepAction extends Action {

  /**
   * The Actor that is to be attacked
   */
  private Actor target;

  /**
   * The direction of incoming attack.
   */
  private String direction;

  /**
   * Random number generator
   */
  private Random rand = new Random();

  /**
   * Weapon used for the attack
   */
  private Weapon weapon;

  /**
   * Constructor.
   *
   * @param target    the Actor to attack
   * @param direction the direction where the attack should be performed (only used for display
   *                  purposes)
   * @param weapon    the weapon used for the attack
   */
  public QuickstepAction(Actor target, String direction, Weapon weapon) {
    this.target = target;
    this.direction = direction;
    this.weapon = weapon;
  }

  /**
   * When executed, the chance to hit of the weapon that the Actor used is computed to determine
   * whether the actor will hit the target. If so, deal damage to the target and determine whether
   * the target is killed. After that, move the actor conducting the attack to a surrounding
   * location if possible.
   *
   * @param actor the actor conducting the attack.
   * @param map   the map the actor is on.
   * @return the result of the attack, e.g. whether the target is killed, etc.
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    AttackAction attackAction = new AttackAction(this.target, this.direction, this.weapon);
    String result = "Using Quick Step, " + attackAction.execute(actor, map);

    for(Exit exit : map.locationOf(actor).getExits()) {
      if (exit.getDestination().canActorEnter(actor)) {
        map.moveActor(actor, exit.getDestination());
        return result + " " + actor + " moves " + exit.getName();
      }
    }
    return result + actor + " couldn't move to a valid location.";
  }

  /**
   * Describes which target the actor is attacking with which weapon and with the quickstep
   * ability.
   *
   * @param actor The actor performing the action.
   * @return a description used for the menu UI
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " attacks " + target + " at " + direction + " with " + weapon
        + " using quick step!";
  }
}
