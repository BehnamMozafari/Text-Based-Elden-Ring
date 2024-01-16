package game.combatants.enemies;

import game.weapons.MoltenHammer;

/**
 * Godrick Soldier class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class GodrickSoldier extends StormveilEnemy{

  /**
   * Constructor.
   *
   */
  public GodrickSoldier() {
    super("Godrick Soldier", 'p', 198, 38, 70);
    addWeaponToInventory(new MoltenHammer());
    this.addCombatantAttackBehaviour(this.getWeaponInventory().get(0));
  }
}
