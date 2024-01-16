package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class for Flask of Crimson Tears which extends the HealItem class
 *
 * @author Josh Ryan, Gunnraj Dhaliwal
 */
public class Flask extends Consumable {

  /**
   * amount the flask heals
   */
  private int healAmount;

  /***
   * Constructor.
   */
  public Flask() {
    super("Flask of Crimson Tears", ' ', false,"drinks", 2);
    this.healAmount = 250;
  }

  /**
   * Method to conduct when using the item, in this case healing the actor
   * @param actor actor using item
   * @return decriptive string
   */
  public String use(Actor actor) {
    actor.heal(healAmount);
    this.decrementUses(actor);
    return actor + " " + this.verb() + " " + this + " to heal " +healAmount + " health points.";
  }

  /**
   * Method to conduct item is out of uses
   * @param actor actor using item
   */
  @Override
  public void noUses(Actor actor) {
    this.removeUseAction();
  }

  /**
   * Gives flask actions back when uses are reset
   */
  public void newUses() {
    this.addUseAction();
  }

  /**
   * Returns a descriptive string
   *
   * @param actor The actor performing the action.
   * @return the text we put on the menu
   */
  @Override
  public String getDescription(Actor actor) {
    return actor + " " + this.verb() + " " + this + " to heal themself by " +
            healAmount + " health points. " + this + " has " + this.getUses() + " use(s) left.";
  }

}
