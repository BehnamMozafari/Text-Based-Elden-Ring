package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.utils.Status;

/**
 * The Cliff environment. Can only be entered by the certain actors that can enter cliffs. Will kill the actor upon entering
 *
 * @author Gunnraj Dhaliwal
 */
public class Cliff extends Ground {
    /**
     * Constructor
     */
    public Cliff() {
        super('+');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_FALL_OFF_CLIFF);
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            new DeathAction(null).execute(location.getActor(), location.map());
        }
    }
}
