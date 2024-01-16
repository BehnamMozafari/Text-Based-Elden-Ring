package game.combatants.enemies;

import game.weapons.Grossmesser;

/**
 * Heavy Skeletal Swordsman class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class HeavySkeletalSwordsman extends Skeleton {

  /**
   * Constructor.
   */
  public HeavySkeletalSwordsman() {
    super("Heavy Skeletal Swordsman", 'q', 153);
    addWeaponToInventory(new Grossmesser());
    this.addCombatantAttackBehaviour(this.getWeaponInventory().get(0));
  }
}
