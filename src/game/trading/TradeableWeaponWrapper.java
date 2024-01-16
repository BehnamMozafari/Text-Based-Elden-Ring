package game.trading;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A wrapper for weapon items so that they can be traded.
 * Implements the Tradeable interface.
 * author Gunnraj Dhaliwal
 */
public class TradeableWeaponWrapper implements Tradeable {
    /**
     * The weapon item to be traded.
     */
    WeaponItem weapon;

    /**
     * Constructs a TradeableWeaponWrapper object with the specified weapon item.
     *
     * @param weapon the weapon item to be wrapped
     */
    public TradeableWeaponWrapper(WeaponItem weapon) {
        this.weapon = weapon;
    }

    /**
     * Removes the tradeable weapon item from the inventory of the actor.
     *
     * @param actorWithTradeable the actor with the tradeable weapon item
     * @throws Exception if the weapon item cannot be removed
     */
    @Override
    public void remove(Actor actorWithTradeable) throws Exception {
        actorWithTradeable.removeWeaponFromInventory(weapon);
    }

    /**
     * Adds the tradeable weapon item to the inventory of the receiving actor.
     *
     * @param actorToGive the actor who is receiving the tradeable weapon item
     */
    @Override
    public void give(Actor actorToGive) {
        actorToGive.addWeaponToInventory(weapon);
    }

    /**
     * Returns a string representation of the tradeable weapon item.
     *
     * @return a string representation of the tradeable weapon item
     */
    @Override
    public String toString() {
        return weapon.toString();
    }
}
