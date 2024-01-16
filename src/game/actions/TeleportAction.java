package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Allows teleportation of an actor to a different map
 *
 * @author Behnam Mozafari
 */
public class TeleportAction extends Action {

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
  public TeleportAction(Location destinationLocation, String destinationName) {
    this.destinationLocation = destinationLocation;
    this.destinationName = destinationName;
  }


  /**
   * Perform the Action.
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return a description of what happened that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    map.moveActor(actor, this.destinationLocation);
    return menuDescription(actor);
  }

  /**
   * Returns a descriptive string
   *
   * @param actor The actor performing the action.
   * @return the text we put on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " travels to " + this.destinationName;
  }
}
