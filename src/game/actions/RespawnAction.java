package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An action that causes the respawning of an actor
 *
 * @author Gunnraj Dhaliwal
 */
public class RespawnAction extends Action {

  /**
   * the actor who will be respawned
   */
  private final Actor actorToRespawn;
  /**
   * the x - coordinate of where the actor will be spawned
   */
  private Location location;

  /**
   * Constructor.
   *
   * @param actorToRespawn actor to be respawned
   * @param location location actor will be respawned
   */
  public RespawnAction(Actor actorToRespawn, Location location) {
    this.actorToRespawn = actorToRespawn;
    this.location = location;
  }

  /**
   * executes the action - respawning the actor at the location
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return string stating a successful respawn
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (map.contains(actor)) {
      map.removeActor(actor);
    }
    map.addActor(this.actorToRespawn, location);
    return this.actorToRespawn + " has awoken!";
  }

  /**
   * Describes which target the actor is attacking with which weapon and with the quickstep
   * ability.
   *
   * @param actor the actor being respawned
   * @return a description used for the menu UI
   */
  @Override
  public String menuDescription(Actor actor) {
    return "Awaken at Last Rested Lost Grace";
  }
}
