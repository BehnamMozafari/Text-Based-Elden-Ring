package game.combatants.enemies;

import game.weapons.Slam;

/**
 * Giant Crayfish Swordsman class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class GiantCrayfish extends Crustacean {

  /**
   * Constructor.
   */
  public GiantCrayfish() {
    super("Giant Crayfish", 'R', 4803, 500, 2374);
    addWeaponToInventory(new Slam(527, 100));
    this.addCombatantAttackBehaviour(this.getWeaponInventory().get(0));
  }
}
