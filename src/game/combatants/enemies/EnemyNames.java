package game.combatants.enemies;

/**
 * Enum for enemy names
 *
 * @author Gunnraj Dhaliwal
 * @version 1.0
 */
public enum EnemyNames {
  LONEWOLF(33),
  DOG(37),
  GIANTDOG(4),
  HEAVYSKELETALSWORDSMAN(27),
  SKELETALBANDIT(27),
  GIANTCRAB(2),
  GIANTCRAYFISH(1),
  PILEOFBONES(100),
  GODRICKSOLDIER(45);

  int spawnChance;

  /**
   * Constructor.
   *
   * @param spawnChance chance to spawn
   */
  EnemyNames(int spawnChance) {
    this.spawnChance = spawnChance;
  }

  /**
   * Returns spawn chance for enemy name
   *
   * @param enemyName enemy
   * @return spawn chance for enemy
   */
  public static int getSpawnChance(EnemyNames enemyName) {
    return enemyName.spawnChance;
  }
}
