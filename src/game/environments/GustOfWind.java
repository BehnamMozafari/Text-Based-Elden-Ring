package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.combatants.enemies.EnemyNames;
import game.combatants.enemies.EnemySpawner;
import game.utils.Utils;

/**
 * Gust of Wind Environment Type. Spawns Dog type enemies (Lone Wolf and Giant Dog)
 *
 * @author Gunnraj Dhaliwal
 * @see Ground
 */
public class GustOfWind extends Ground implements EnemyGroundSpawn {

  /**
   * Constructor
   *
   * @see Ground
   */
  public GustOfWind() {
    super('&');
  }

  /**
   * Every in game tick, the Gust of Wind will attempt to spawn either a Lone Wolf or Giant Dog
   * (depending on location)
   *
   * @param location location class that this ground subclass is stored on
   */
  @Override
  public void tick(Location location) {
    spawnEnemy(location);
  }

  /**
   * Checks the location (whether on the left or right of GameMap) using Utils class and then
   * attempts to spawn the respective enemy (Lone Wolf on the left, Giant Dog on the right) using
   * EnemySpawner class
   *
   * @param location location class that this ground subclass is stored on
   * @see Utils
   * @see EnemySpawner
   */
  @Override
  public void spawnEnemy(Location location) {
    if (Utils.isLocationLeftOrRight(location) == 'l') {
      EnemySpawner.spawnEnemy(location, EnemyNames.LONEWOLF, false);
    } else {
      EnemySpawner.spawnEnemy(location, EnemyNames.GIANTDOG, false);
    }
  }
}
