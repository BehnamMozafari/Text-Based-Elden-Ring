package game.combatants.enemies;

import game.weapons.Slam;

/**
 * Giant Crab class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class GiantCrab extends Crustacean{

  /**
   * Constructor.
   */
  public GiantCrab() {
    super("Giant Crab", 'C', 407,318, 4961);
    addWeaponToInventory(new Slam(208, 90));
    this.addCombatantAttackBehaviour(this.getWeaponInventory().get(0));
  }
}
