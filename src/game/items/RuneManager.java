package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.resethandling.ResetManager;
import java.util.HashMap;

/**
 * Manages runes and the actors who hold them
 * @author Gunnraj Dhaliwal Modified by: Behnam Mozafari
 */
public class RuneManager {

  /**
   * hashmap that maps actors to their runes
   */
  private HashMap<Actor, Runes> runeTable = new HashMap<>();
  /**
   * The runes dropped by the player upon death
   */
  private Runes droppedFromPlayerDeathRunes;
  /**
   * used to ensure RuneManager can only have 1 instance
   */
  private static RuneManager instance;

  /**
   * Constructor
   */
  private RuneManager() {
  }

  /**
   * method to get instance of a RuneManager. Follows the singleton pattern
   *
   * @return instance of the RuneManager
   */
  public static RuneManager getInstance() {
    if (instance == null) {
      instance = new RuneManager();
    }
    return instance;
  }

  /**
   * Allows the adding of actors and their runes into the RuneManager
   *
   * @param actor The actor who is having their runes registered
   * @param runes The actor's runes getting registered
   */
  public void registerActorRunes(Actor actor, Runes runes) {
    this.runeTable.put(actor, runes);
  }

  /**
   * removes actor and their runes from the RuneManager
   *
   * @param actor The actor being de - registered
   */
  public void removeActorRunes(Actor actor) {
    this.runeTable.remove(actor);
  }

  public void addToActorRunes(Actor actor, int amount) {
    runeTable.get(actor).addAmount(amount);
  }

  public void removeFromActorRunes(Actor actor, int amount) throws Exception {
    runeTable.get(actor).removeAmount(amount);
  }

  /**
   * Allows for access into the specified actor's runes
   *
   * @param actor the actor whose runes you want to access
   * @return the runes belonging to the parameter actor
   */
  public Runes getActorRunes(Actor actor) {
    return runeTable.get(actor);
  }

  /**
   * Transfers all runes from giver actor to receiver actor, removes receiver from runeTable
   *
   * @param giver    actor to give runes
   * @param receiver actor to receive runes
   */
  public void transferRunes(Actor giver, Actor receiver) {
    Runes giverRunes = this.getActorRunes(giver);
    this.removeActorRunes(giver);
    Runes receiverRunes = this.getActorRunes(receiver);
    receiverRunes.addAmount(giverRunes.getAmount());
    this.registerActorRunes(receiver, receiverRunes);
  }

  /**
   * Sets the droppedFromPlayerDeathRunes attribute, and adds the previous
   * droppedFromPlayerDeathRunes to the reset manager as they should be reset
   *
   * @param droppedFromPlayerDeathRunes The runes that were on the player that are being dropped due
   *                                    to death
   */
  public void setDroppedFromPlayerDeathRunes(Runes droppedFromPlayerDeathRunes) {
    // if there is already droppedPlayerRunes, then they will be registered to be reset
    // this should only occur when the player dies
    if (this.droppedFromPlayerDeathRunes != null) {
      ResetManager.getInstance().registerResettable(this.droppedFromPlayerDeathRunes);
    }
    if (droppedFromPlayerDeathRunes != null) {
      droppedFromPlayerDeathRunes.setIsPlayerRunes(true);
    }
    this.droppedFromPlayerDeathRunes = droppedFromPlayerDeathRunes;
  }
}
