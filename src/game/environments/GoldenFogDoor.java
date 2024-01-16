package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.combatants.CombatantType;
import game.utils.Status;

/**
 * Golden Fog Door class, lets player travel to other maps
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class GoldenFogDoor extends Ground {

  /**
   * location to travel to in world
   */
  private final Location destinationLocation;

  /**
   * name of destination
   */
  private final String destinationName;

  /**
   * Constructor.
   *
   * @param destinationLocation location to travel to in world
   * @param destinationName name of destination map
   */
  public GoldenFogDoor(Location destinationLocation, String destinationName) {
    super('D');
    this.destinationLocation = destinationLocation;
    this.destinationName = destinationName;
  }

  /**
   * Allows the player to travel between maps
   *
   * @param actor     the Actor acting
   * @param location  the current Location
   * @param direction the direction of the Ground from the Actor
   * @return list of allowable actions
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    if (actor.hasCapability(Status.IS_PLAYER)) {
      return new ActionList(new TeleportAction(this.destinationLocation, this.destinationName));
    }
    return new ActionList();
  }

  /**
   * Check if actor can enter ground
   *
   * @param actor the Actor to check
   * @return boolean if they can enter or not
   */
  @Override
  public boolean canActorEnter(Actor actor) {
    return actor.hasCapability(CombatantType.FRIENDLY);
  }
}
