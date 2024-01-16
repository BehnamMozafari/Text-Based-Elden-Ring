package game.environments;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for all ground subclasses that should spawn enemies on them
 *
 * @author Gunnraj
 */
public interface EnemyGroundSpawn {

  /**
   * Spawns enemy at location
   *
   * @param location location to be spawned at
   */
  public void spawnEnemy(Location location);
}
