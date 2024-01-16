package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.combatants.enemies.EnemyNames;
import game.combatants.enemies.EnemySpawner;

/**
 * Barrack Environment. Spawns Godrick Soldiers.
 *
 * @author Gunnraj Dhaliwal
 */
public class Barrack extends Ground implements EnemyGroundSpawn{
    public Barrack() {
        super('B');
    }

    @Override
    public void tick(Location location) {
        spawnEnemy(location);
    }

    @Override
    public void spawnEnemy(Location location) {
        EnemySpawner.spawnEnemy(location, EnemyNames.GODRICKSOLDIER, false);
    }
}
