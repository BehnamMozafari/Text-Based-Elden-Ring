package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.UseConsumableAction;


/**
 * Consumable abstract class
 *
 * @author Josh Ryan, Gunnraj Dhaliwal
 */
public abstract class Consumable extends Item {

    private String verb;

    /**
     * The maximum uses the consumable can have
     */
    private final int maxUses;

    /**
     * The amount of uses left on the consumable
     */
    private int uses;

    /**
     * Action that the consumable can do
     */
    private UseConsumableAction useConsumableAction;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Consumable(String name, char displayChar, boolean portable, String verb, int uses) {
        super(name, displayChar, portable);
        this.verb = verb;
        this.uses = uses;
        this.maxUses = uses;
        this.useConsumableAction = new UseConsumableAction(this);
        this.addUseAction();
    }

    /**
     * A verb to use when displaying the results of using this consumable
     *
     * @return String, e.g. "heals", "drinks"
     */
    public String verb() {
        return this.verb;
    }

    /**
     * The amount of uses left on the consumable
     *
     * @return the uses left
     */
    public int getUses() {
        return uses;
    }

    /**
     * Method to conduct when using the item, e.g. removing a use
     */
    public abstract String use(Actor actor);

    /**
     * Method to reduce uses left on consumable
     */
    public void decrementUses(Actor actor) {
        this.uses -= 1;
        if (this.uses == 0) {
            this.noUses(actor);
        }
    }

    /**
     * Method to be run when consumable is out of uses
     */
    public abstract void noUses(Actor actor);

    /**
     * Method to conduct when resetting the uses of the Consumable
     */
    public void resetUses() {
        if (this.uses == 0) {
            this.newUses();
        }
        this.uses = this.maxUses;
    }

    /**
     * Method to reset capability of consumable
     */
    public abstract void newUses();

    /**
     * Method to remove consumable use action
     */
    protected void removeUseAction() {
        this.removeAction(useConsumableAction);
    }

    /**
     * Method to add consumable use action
     */
    protected void addUseAction() {
        this.addAction(useConsumableAction);
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    public abstract String getDescription(Actor actor);

}
