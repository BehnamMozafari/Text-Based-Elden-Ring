package game.combatants.enemies;

import game.combatants.Combatant;
import game.combatants.CombatantType;

/**
 * Skeleton abstract class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public abstract class Skeleton extends Combatant {

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Skeleton(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints, 10, true);
    this.addCapability(CombatantType.SKELETON);
  }
}
