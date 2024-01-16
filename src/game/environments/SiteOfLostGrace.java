package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SiteOfLostGraceAction;
import game.utils.Status;

/**
 * The Site of Lost Grace where the player can rest to reset the world
 *
 * @author Josh Ryan, Gunnraj Dhaliwal
 */
public class SiteOfLostGrace extends Ground {

  /**
   * Name of a single site of lost grace.
   */
  private String name;

  /**
   * Constructor.
   *
   * @param name name of the site of lost grace.
   */
  public SiteOfLostGrace(String name) {
    super('U');
    this.name = name;
  }

  /**
   * Allows the player to rest at site of grace
   *
   * @param actor     the Actor acting
   * @param location  the current Location
   * @param direction the direction of the Ground from the Actor
   * @return list of allowable actions
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    if (actor.hasCapability(Status.IS_PLAYER)) {
      return new ActionList(new SiteOfLostGraceAction(location));
    }
    return new ActionList();
  }
}
