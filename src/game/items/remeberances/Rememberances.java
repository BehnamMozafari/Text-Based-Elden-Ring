package game.items.remeberances;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class to represent Rememberances.
 *
 * @author Gunnraj Dhaliwal
 */
public abstract class Rememberances extends Item{

    /**
     * A list of weapons that this rememberance can be traded for
     */
    List<WeaponItem> tradeableWeapons = new ArrayList<>();
    /**
     * A list of items that this rememberance can be traded for
     */
    List<Item> tradeableItems = new ArrayList<>();
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public Rememberances(String name, char displayChar) {
        super(name, displayChar, false);
    }

    /**
     * Returns the list of weapons this rememberance can be traded for
     * @return list of weapons this rememberance can be traded for
     */
    public List<WeaponItem> weaponsCanBeTradedFor() {
        return Collections.unmodifiableList(tradeableWeapons);
    }

    /**
     * Returns the list of items this rememberance can be traded for
     * @return list of items this rememberance can be traded for
     */
    public List<Item> itemsCanBeTradedFor() {
        return Collections.unmodifiableList(tradeableItems);
    }
}
