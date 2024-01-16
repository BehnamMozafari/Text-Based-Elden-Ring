package game.trading;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that specifies methods required for trading
 *
 * @author Gunnraj Dhaliwal
 */
public interface Tradeable {
    /**
     * allows for the removal of the tradeable from an actor's inventory
     * @param actorWithTradeable the actor with the tradeable
     * @throws Exception if the item cannot be removed
     */
    void remove(Actor actorWithTradeable) throws Exception;

    /**
     * Allows for the tradeable to be placed within the actor's inventory
     * @param actorToGive the actor who is receiving the tradeable
     */
    void give(Actor actorToGive);
}
