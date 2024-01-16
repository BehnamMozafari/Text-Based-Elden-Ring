package game.player;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Club;

/**
 * The Wretch archetype class
 *
 * @author Josh Ryan
 */
public class Wretch implements Archetype {

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
   * @return a Club
   */
  @Override
  public WeaponItem startingWeapon() {
    return new Club();
  }

  /**
   * The name of the archetype
   *
   * @return "Wretch" string
   */
  @Override
  public String toString() {
    return "Wretch";
  }
}
