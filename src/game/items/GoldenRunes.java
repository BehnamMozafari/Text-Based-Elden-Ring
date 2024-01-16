package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for Golden Runes consumable
 *
 * @author Josh Ryan, Gunnraj Dhaliwal
 */
public class GoldenRunes extends Consumable {

    /**
     * Constructor
     */
    public GoldenRunes() {
        super("Golden Runes", '*', true, "uses", 1);
    }

    /**
     * Inform a carried Item of the passage of time.
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        portable = false;
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        portable = true;
    }

    /**
     * THe allowable actions for the Golden Rune. Will only allow consumption when it is stored in an actor's inventory
     * @return An unmodifiable list of actions that the actor can do to this rune - which is only to consume the rune
     */
    @Override
    public List<Action> getAllowableActions() {
        if (!portable) {
            return super.getAllowableActions();
        }
        else {
            return Collections.unmodifiableList(new ArrayList<>());
        }
    }

    /**
     * Method to conduct when using the item, in this case giving the actor runes
     * @param actor actor using item
     * @return decriptive string
     */
    @Override
    public String use(Actor actor) {
        int lowerBound = 200;
        int upperBound = 10000;
        int runeAmount = Utils.getRandomInt(lowerBound, upperBound);
        RuneManager.getInstance().addToActorRunes(actor, runeAmount);
        this.decrementUses(actor);
        return actor +" " +this.verb() +" " +this +" and receives " +runeAmount +" runes";
    }

    /**
     * Method to conduct item is out of uses
     * @param actor actor using item
     */
    @Override
    public void noUses(Actor actor) {
        actor.removeItemFromInventory(this);
        this.removeUseAction();
    }

    /**
     * Empty implementation of the newUses() method from Consumable, since the golden rune will never need to be reset.
     */
    @Override
    public void newUses() {}

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String getDescription(Actor actor) {
        return "Consume " +this +" to receive runes";
    }
}
