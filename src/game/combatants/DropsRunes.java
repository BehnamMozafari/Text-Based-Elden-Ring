package game.combatants;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.RuneManager;
import game.items.Runes;
import game.utils.Utils;

/**
 * Interface for enemies that drop runes
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public interface DropsRunes {

  /**
   * Generates a random amount of runes within a range specific to each rune dropping class,
   * registers the rune dropping class instance and number of runes to the current instance of
   * RuneManager.
   *
   * @param actor      actor to register runes to
   * @param lowerBound lower bound (inclusive) of runes
   * @param upperBound upper bound (inclusive) of runes
   */
  default void runeGenerator(Actor actor, int lowerBound, int upperBound) {
    Runes runes = new Runes(Utils.getRandomInt(lowerBound, upperBound + 1));
    RuneManager runeManager = RuneManager.getInstance();
    runeManager.registerActorRunes(actor, runes);
  }
}
