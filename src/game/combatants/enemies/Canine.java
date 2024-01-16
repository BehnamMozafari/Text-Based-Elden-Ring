package game.combatants.enemies;

import game.combatants.Combatant;
import game.combatants.CombatantType;
import game.combatants.DropsRunes;

/**
 * Canine abstract class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public abstract class Canine extends Combatant implements DropsRunes {

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Canine(String name, char displayChar, int hitPoints, int minRunes, int maxRunes) {
    super(name, displayChar, hitPoints, 10, true);
    this.addCapability(CombatantType.CANINE);
    runeGenerator(this, minRunes, maxRunes);
  }
}
