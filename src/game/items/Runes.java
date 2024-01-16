package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpRuneAction;
import game.trading.Tradeable;
import game.resethandling.Resettable;

/**
 * The class that represents runes. Has an internal attribute representing the number of runes it is
 * worth
 *
 * @author Gunnraj Dhaliwal
 * @see RuneManager
 * @see Resettable
 */
public class Runes extends Item implements Resettable, Tradeable {

  /**
   * The amount of runes it is worth
   */
  private int amount;
  /**
   * Determines whether this rune should be reset on its next turn
   */
  private Boolean toBeReset = false;
  /**
   * Determines whether these are the player's runes
   */
  private Boolean isPlayerRunes = false;

  /**
   * Constructor
   *
   * @param amount the amount the Rune will be worth
   */
  public Runes(int amount) {
    super("Runes", '$', false);
    this.amount = amount;
  }

  /**
   * returns the worth of the rune
   *
   * @return amount the rune is worth
   */
  public int getAmount() {
    return amount;
  }

  /**
   * Adds a given amount to this rune amount
   *
   * @param amount amount to add to the rune
   */
  public void addAmount(int amount) {
    if (amount > 0 && this.amount > Integer.MAX_VALUE - amount) {
      this.amount = Integer.MAX_VALUE;
    }
    else {
      this.amount += amount;
    }
  }

  /**
   * removes a given amount from the rune. If the amount is greater than the rune is worth, throws
   * an Exception
   *
   * @param amount amount to remove from the rune
   * @throws Exception when the amount to remove is greater than the rune is worth
   */
  public void removeAmount(int amount) throws Exception {
    if (this.amount - amount < 0) {
      throw new Exception("Removing this amount of runes will cause a negative amount of runes!");
    } else {
      this.amount -= amount;
    }
  }

  /**
   * Sets whether this is the player's rune
   *
   * @param isPlayerRunes a boolean representing whether it is the player's runes
   */
  public void setIsPlayerRunes(boolean isPlayerRunes) {
    this.isPlayerRunes = isPlayerRunes;
  }

  /**
   * returns whether this is the player's runes
   *
   * @return a boolean representing whether it is the player's runes
   */
  public boolean IsPlayerRunes() {
    return this.isPlayerRunes;
  }

  /**
   * Allows the rune to experience time. Will be removed during a "tick" if it toBeReset is true.
   *
   * @param currentLocation The location of the ground on which we lie.
   */
  @Override
  public void tick(Location currentLocation) {
    if (toBeReset) {
      toBeReset = false;
      currentLocation.removeItem(this);
    }
  }

  @Override
  public void togglePortability() {
    portable = !portable;
    if (portable) {
      // runes can be interacted with through this pick up action (which destroys the rune)
      super.addAction(new PickUpRuneAction(this));
    }
  }

  /**
   * Runes are unable to be conventionally picked up
   *
   * @param actor the actor picking up the rune
   * @return The PickUpRuneAction for this rune
   */
  @Override
  public PickUpAction getPickUpAction(Actor actor) {
      return null;
  }

  /**
   * Will set the rune to be reset on its next tick
   */
  @Override
  public void reset() {
    if (portable) {
      toBeReset = true;
    }
  }

  @Override
  public void remove(Actor actorWithTradeable) throws Exception{
    try {
      RuneManager.getInstance().removeFromActorRunes(actorWithTradeable, this.getAmount());
    }
    catch (Exception e) {
      throw new Exception(actorWithTradeable +" does not possess enough Runes!");
    }
  }

  @Override
  public void give(Actor actorToGive) {
    RuneManager.getInstance().addToActorRunes(actorToGive, this.getAmount());
  }

  @Override
  public String toString() {
    return getAmount() +" Runes";
  }
}
