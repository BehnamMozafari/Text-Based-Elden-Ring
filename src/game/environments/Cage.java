package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.combatants.enemies.EnemyNames;
import game.combatants.enemies.EnemySpawner;

/**
 * Cage environment. Spawns Dogs.
 *
 * @author Gunnraj
 */
public class Cage extends Ground implements EnemyGroundSpawn{
    /**
     * Constructor
     */
    public Cage() {
        super('<');
    }

    @Override
    public void tick(Location location) {
        spawnEnemy(location);
    }
    @Override
    public void spawnEnemy(Location location) {
        EnemySpawner.spawnEnemy(location, EnemyNames.DOG, false);
    }
}
