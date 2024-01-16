package game.trading;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * A wrapper for items so that they can be traded
 * Implements the Tradeable interface
 *
 * @author Gunnraj Dhaliwal
 */
public class TradeableItemWrapper implements Tradeable {
    /**
     * The item to be traded
     */
    Item item;

    /**
     * Constructs a TradeableItemWrapper object with the specified item.
     *
     * @param item the item to be wrapped
     */
    public TradeableItemWrapper(Item item) {
        this.item = item;
    }

    /**
     * Removes the tradeable item from the inventory of the actor.
     *
     * @param actorWithTradeable the actor with the tradeable item
     * @throws Exception if the item cannot be removed
     */
    @Override
    public void remove(Actor actorWithTradeable) throws Exception {
        actorWithTradeable.removeItemFromInventory(item);
    }

    /**
     * Adds the tradeable item to the inventory of the receiving actor.
     *
     * @param actorToGive the actor who is receiving the tradeable item
     */
    @Override
    public void give(Actor actorToGive) {
        actorToGive.addItemToInventory(item);
    }

    /**
     * Returns a string representation of the tradeable item.
     *
     * @return a string representation of the tradeable item
     */
    @Override
    public String toString() {
        return item.toString();
    }
}
