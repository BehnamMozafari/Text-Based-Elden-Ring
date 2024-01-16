package game.combatants.enemies;

import game.combatants.Combatant;
import game.combatants.CombatantType;
import game.combatants.DropsRunes;
import game.utils.Status;

/**
 * Crustacean abstract class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public abstract class Crustacean extends Combatant implements DropsRunes {

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Crustacean(String name, char displayChar, int hitPoints, int minRunes, int maxRunes) {
    super(name, displayChar, hitPoints, 10, true);
    runeGenerator(this, minRunes, maxRunes);
    this.addCapability(CombatantType.CRUSTACEAN);
    this.addCapability(Status.IMMUNE_TO_FIRE);
  }
}
