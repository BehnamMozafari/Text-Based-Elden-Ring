package game.player;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Interface to be used by combat archetypes
 *
 * @author Josh Ryan
 */
public interface Archetype {

  /**
   * The amount of health a player with this archetype starts with
   *
   * @return the health in hit points
   */
  int hitPoints();

  /**
   * The starting weapon of this archetype
   *
   * @return a WeaponItem representing the starting weapon of this archetype
   */
  WeaponItem startingWeapon();

  /**
   * The name of the archetype
   *
   * @return the name of the archetype
   */
  String toString();

}
