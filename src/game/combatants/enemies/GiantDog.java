package game.combatants.enemies;

import game.weapons.Slam;

/**
 * Giant Dog class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class GiantDog extends Canine {

  /**
   * Constructor.
   */
  public GiantDog() {
    super("Giant Dog", 'G', 693, 313, 1808);
    addWeaponToInventory(new Slam(314, 90));
    this.addCombatantAttackBehaviour(this.getWeaponInventory().get(0));
  }
}