package game.combatants.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Dog class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class Dog extends StormveilEnemy{


  /**
   * Constructor.
   *
   */
  public Dog() {
    super("Dog", 'a', 104, 52, 1390);
    addCombatantAttackBehaviour(null);
  }

  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(101, "bites", 93);
  }
}
