package game.player;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Class that returns an instance of the player for behaviours or allows only specific interactions
 * with the player.
 *
 * @author Gunnraj Dhaliwal
 */
public class PlayerManager {

  /**
   * The player of the game
   */
  private Player player;
  /**
   * used for singleton design pattern
   */
  private static PlayerManager instance;

  /**
   * Constructor
   */
  private PlayerManager() {
  }

  /**
   * Used for singleton design pattern. Allows only 1 instance of the manager to exist at any time
   *
   * @return PlayerManager instance
   */
  public static PlayerManager getInstance() {
    if (instance == null) {
      instance = new PlayerManager();
    }
    return instance;
  }

  /**
   * @param player the player of the game
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * @return the player of the game
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Updates the player class' internal spawn location
   *
   * @param location location to update to
   */
  public void updatePlayerSpawnLocation(Location  location) {
    player.setLastRested(location);
  }

  /**
   * Gets the players last location
   *
   * @return player's last location
   */
  public Location getPlayerLastLocation() {
    return player.getLastLocation();
  }
}
