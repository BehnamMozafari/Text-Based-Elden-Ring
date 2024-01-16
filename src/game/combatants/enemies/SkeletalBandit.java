package game.combatants.enemies;

import game.weapons.Scimitar;

/**
 * Skeletal Bandit class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class SkeletalBandit extends Skeleton {

  /**
   * Constructor
   */
  public SkeletalBandit() {
    super("Skeletal Bandit", 'b', 184);
    addWeaponToInventory(new Scimitar());
    this.addCombatantAttackBehaviour(this.getWeaponInventory().get(0));
  }
}