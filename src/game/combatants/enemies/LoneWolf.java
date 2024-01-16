package game.combatants.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Lone Wolf Class
 *
 * @author Adrian Kristanto, Behnam Mozafari
 */
public class LoneWolf extends Canine {

  /**
   * Constructor.
   */
  public LoneWolf() {
    super("Lone Wolf", 'h', 102,55, 1470);
    this.addCombatantAttackBehaviour(null);
  }

  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(97, "bites", 95);
  }

}
