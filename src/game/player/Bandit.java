package game.player;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.GreatKnife;

/**
 * The Bandit archetype class
 *
 * @author Josh Ryan
 */
public class Bandit implements Archetype {

  /**
   * The amount of health a player with this archetype starts with
   *
   * @return 414 hit points
   */
  @Override
  public int hitPoints() {
    return 414;
  }

  /**
   * The starting weapon of this archetype
   *
   * @return a GreatKnife
   */
  @Override
  public WeaponItem startingWeapon() {
    return new GreatKnife();
  }

  /**
   * The name of the archetype
   *
   * @return "Bandit" string
   */
  @Override
  public String toString() {
    return "Bandit";
  }
}
