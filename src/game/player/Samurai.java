package game.player;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Uchigatana;

/**
 * The Samurai archetype class
 *
 * @author Josh Ryan
 */
public class Samurai implements Archetype {

  /**
   * The amount of health a player with this archetype starts with
   *
   * @return 455 hit points
   */
  @Override
  public int hitPoints() {
    return 455;
  }

  /**
   * The starting weapon of this archetype
   *
   * @return a Uchigatana
   */
  @Override
  public WeaponItem startingWeapon() {
    return new Uchigatana();
  }

  /**
   * The name of the archetype
   *
   * @return "Samurai" string
   */
  @Override
  public String toString() {
    return "Samurai";
  }
}
