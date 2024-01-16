package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonSignAction;
import game.utils.Status;

/**
 * The Summon Sign used to spawn Allies and Invaders
 *
 * @author Josh Ryan
 */
public class SummonSign extends Ground {

    /**
     * Constructor.
     *
     */
    public SummonSign() {
        super('=');
    }

    /**
     * Allows the player to use the Summon Sign
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (actor.hasCapability(Status.IS_PLAYER)) {
            return new ActionList(new SummonSignAction(location));
        }
        return new ActionList();
    }

}
