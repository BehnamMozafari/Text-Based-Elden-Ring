package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.combatants.enemies.EnemyNames;
import game.combatants.enemies.EnemySpawner;
import game.utils.Utils;

/**
 * Puddle of Water Environment Type. Spawns Crustacean type enemies (Giant Crab and Giant Crayfish)
 *
 * @author Gunnraj Dhaliwal
 * @see Ground
 */
public class PuddleOfWater extends Ground implements EnemyGroundSpawn {

  /**
   * Constructor
   *
   * @see Ground
   */
  public PuddleOfWater() {
    super('~');
  }

  /**
   * Every in game tick, the Puddle of Water will attempt to spawn either a Giant Crab or Giant
   * Crayfish (depending on location)
   *
   * @param location location class that this ground subclass is stored on
   */
  @Override
  public void tick(Location location) {
    spawnEnemy(location);
  }

  /**
   * Checks the location (whether on the left or right of GameMap) using Utils class and then
   * attempts to spawn the respective enemy (Giant Crab on the left, Giant Crayfish on the right)
   * using EnemySpawner class
   *
   * @param location location class that this ground subclass is stored on
   * @see Utils
   * @see EnemySpawner
   */
  @Override
  public void spawnEnemy(Location location) {
    if (Utils.isLocationLeftOrRight(location) == 'l') {
      EnemySpawner.spawnEnemy(location, EnemyNames.GIANTCRAB, false);
    } else {
      EnemySpawner.spawnEnemy(location, EnemyNames.GIANTCRAYFISH, false);
    }
  }
}
