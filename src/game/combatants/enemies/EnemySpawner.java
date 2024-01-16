package game.combatants.enemies;

import edu.monash.fit2099.engine.positions.Location;
import game.resethandling.ResetManager;
import game.utils.Utils;

/**
 * Factory class that handles the spawning of enemies at locations.
 *
 * @author Gunnraj
 */
public class EnemySpawner {

  /**
   * Static method that allows for encapsulation of spawn logic for enemies
   *
   * @param location        location where the enemy will be spawned
   * @param enemyName       An enum describing the type of enemy to be spawned
   * @param guaranteedSpawn a boolean that can be set as true to ensure spawn occurs
   */
  public static void spawnEnemy(Location location, EnemyNames enemyName, Boolean guaranteedSpawn) {
    if (enemyName == EnemyNames.LONEWOLF) {
      if (Utils.percentageCheck(EnemyNames.getSpawnChance(EnemyNames.LONEWOLF))
          && !location.containsAnActor() || guaranteedSpawn) {
        LoneWolf loneWolf = new LoneWolf();
        ResetManager.getInstance().registerResettable(loneWolf);
        location.addActor(loneWolf);
      }
    } else if (enemyName == EnemyNames.DOG) {
      if (Utils.percentageCheck(EnemyNames.getSpawnChance(EnemyNames.DOG))
          && !location.containsAnActor() || guaranteedSpawn) {
        Dog dog = new Dog();
        ResetManager.getInstance().registerResettable(dog);
        location.addActor(dog);
      }
    } else if (enemyName == EnemyNames.GIANTDOG) {
      if (Utils.percentageCheck(EnemyNames.getSpawnChance(EnemyNames.GIANTDOG))
          && !location.containsAnActor() || guaranteedSpawn) {
        GiantDog giantDog = new GiantDog();
        ResetManager.getInstance().registerResettable(giantDog);
        location.addActor(giantDog);
      }
    } else if (enemyName == EnemyNames.HEAVYSKELETALSWORDSMAN) {
      if (Utils.percentageCheck(EnemyNames.getSpawnChance(EnemyNames.HEAVYSKELETALSWORDSMAN))
          && !location.containsAnActor() || guaranteedSpawn) {
        HeavySkeletalSwordsman heavySkeletalSwordsman = new HeavySkeletalSwordsman();
        ResetManager.getInstance().registerResettable(heavySkeletalSwordsman);
        location.addActor(heavySkeletalSwordsman);
      }
    } else if (enemyName == EnemyNames.SKELETALBANDIT) {
      if (Utils.percentageCheck(EnemyNames.getSpawnChance(EnemyNames.SKELETALBANDIT))
          && !location.containsAnActor() || guaranteedSpawn) {
        SkeletalBandit skeletalBandit = new SkeletalBandit();
        ResetManager.getInstance().registerResettable(skeletalBandit);
        location.addActor(skeletalBandit);
      }
    } else if (enemyName == EnemyNames.GIANTCRAB) {
      if (Utils.percentageCheck(EnemyNames.getSpawnChance(EnemyNames.GIANTCRAB))
          && !location.containsAnActor() || guaranteedSpawn) {
        GiantCrab giantCrab = new GiantCrab();
        ResetManager.getInstance().registerResettable(giantCrab);
        location.addActor(giantCrab);
      }
    } else if (enemyName == EnemyNames.GIANTCRAYFISH) {
      if (Utils.percentageCheck(EnemyNames.getSpawnChance(EnemyNames.GIANTCRAYFISH))
          && !location.containsAnActor() || guaranteedSpawn) {
        GiantCrayfish giantCrayfish = new GiantCrayfish();
        ResetManager.getInstance().registerResettable(giantCrayfish);
        location.addActor(giantCrayfish);
      }
    } else if (enemyName == EnemyNames.GODRICKSOLDIER) {
      if (Utils.percentageCheck(EnemyNames.getSpawnChance(EnemyNames.GODRICKSOLDIER))
          && !location.containsAnActor() || guaranteedSpawn) {
        GodrickSoldier godrickSoldier = new GodrickSoldier();
        ResetManager.getInstance().registerResettable(godrickSoldier);
        location.addActor(godrickSoldier);
      }
    }
  }
}
