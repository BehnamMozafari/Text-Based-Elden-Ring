package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.combatants.enemies.EnemyNames;
import game.combatants.enemies.EnemySpawner;
import game.utils.Utils;

/**
 * Graveyard Environment Type. Spawns skeleton type enemies (Heavy Skeletal Swordsman and Skeletal
 * Bandit)
 *
 * @author Gunnraj Dhaliwal
 * @see Ground
 */
public class Graveyard extends Ground implements EnemyGroundSpawn {

  /**
   * Constructor
   *
   * @see Ground
   */
  public Graveyard() {
    super('n');
  }

  /**
   * Every in game tick, the Gust of Wind will attempt to spawn either a Heavy Skeletal Swordsman or
   * Skeletal Bandit (depending on location)
   *
   * @param location location class that this ground subclass is stored on
   */
  @Override
  public void tick(Location location) {
    spawnEnemy(location);
  }

  /**
   * Checks the location (whether on the left or right of GameMap) using Utils class and then
   * attempts to spawn the respective enemy (Heavy Skeletal Swordsman on the left, Skeletal Bandit
   * on the right) using EnemySpawner class
   *
   * @param location location class that this ground subclass is stored on
   * @see Utils
   * @see EnemySpawner
   */
  @Override
  public void spawnEnemy(Location location) {
    if (Utils.isLocationLeftOrRight(location) == 'l') {
      EnemySpawner.spawnEnemy(location, EnemyNames.HEAVYSKELETALSWORDSMAN, false);
    } else {
      EnemySpawner.spawnEnemy(location, EnemyNames.SKELETALBANDIT, false);
    }
  }
}
