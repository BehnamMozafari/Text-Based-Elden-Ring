package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.trading.Tradeable;

public class TradeAction extends Action {
    /**
     * The actor buying the item
     */
    Actor seller;
    /**
     * The actor selling the item
     */
    Actor buyer;
    /**
     * An item being traded
     */
    Tradeable tradeable1;
    /**
     * The other item being traded
     */
    Tradeable tradeable2;

    /**
     * Constructor
     * @param seller The actor selling the item
     * @param buyer The actor buying the item
     * @param tradeable1 The first item being traded
     * @param tradeable2 The other item being traded
     */
    public TradeAction(Actor seller, Actor buyer, Tradeable tradeable1, Tradeable tradeable2) {
        this.seller = seller;
        this.buyer = buyer;
        this.tradeable1 = tradeable1;
        this.tradeable2 = tradeable2;
    }

    /**
     * Trades tradeable1 for tradeable2. If an error occurs (an actor does not possess the item), then the trade is
     * halted and tradeables returned
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string detailing either the success or the failure of the trade
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // attempting to remove tradeable from first actor
        // On failure we return failure message
        try {
            tradeable1.remove(seller);
        }
        catch (Exception e) {
            return "Trade Failed! " +e.getMessage();
        }

        // attempting to remove tradeable from second actor
        // On failure we return failure message and return tradeable back to first actor
        try {
            tradeable2.remove(buyer);
        }
        catch (Exception e) {
            tradeable1.give(seller);
            return "Trade Failed! " +e.getMessage();
        }

        // giving each actor the tradeables they request
        tradeable1.give(buyer);
        tradeable2.give(seller);

        return buyer +" successfully traded " +tradeable1 +" for " +tradeable2 +" from " +seller;
    }

    /**
     * A description detailing the trade, specifying the items to be traded and which actors would participate
     * @param actor The actor performing the action.
     * @return A string detailing the trade
     */
    @Override
    public String menuDescription(Actor actor) {
        return buyer +" buys " +tradeable1 +" from " +seller +" for " +tradeable2;
    }
}
