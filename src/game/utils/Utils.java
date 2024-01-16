package game.utils;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.environments.Barrack;
import game.environments.Cage;
import game.environments.Cliff;
import game.environments.Dirt;
import game.environments.Floor;
import game.environments.Graveyard;
import game.environments.GustOfWind;
import game.environments.PuddleOfWater;
import game.environments.SummonSign;
import game.environments.Wall;
import java.util.List;
import java.util.Random;

/**
 * A class with multiple helpful functions
 * @author Adrian Kristanto
 * @author Gunnraj Dhaliwal
 */
public class Utils {
  /**
   * Get random integer less than bound but greater than or equal to 0
   *
   * @param bound upper bound (not inclusive) of return value
   * @return random integer
   */
  public static int getRandomInt(int bound) {
    return bound > 0 ? new Random().nextInt(bound) : 0;
  }

  /**
   * Get random integer between lowerbound (inclusive) but less than upperbound (exclusive)
   *
   * @param lowerBound lower bound (inclusive) of return value
   * @param upperBound upper bound (not inclusive) of return value
   * @return random integer
   */
  public static int getRandomInt(int lowerBound, int upperBound) {
    int range = upperBound - lowerBound + 1;
    return new Random().nextInt(range) + lowerBound;
  }

  /**
   * Return boolean if a randomly generated number between 0 and 100 (inclusive) is  less than
   * percentage
   *
   * @param percentage percentage to check
   * @return boolean if check was true or not
   */
  public static boolean percentageCheck(int percentage) {
    return getRandomInt(101) < percentage;
  }

  /**
   * Checks whether given location is on the left or right side of the map using the length of the
   * game map.
   *
   * @param location location to be checked
   * @return 'l' or 'r' if left or right side.
   */
  public static char isLocationLeftOrRight(Location location) {
    int currentMapLength = location.map().getXRange().max();

    if (location.x() < currentMapLength / 2) {
      return 'l';
    }
    else {
      return 'r';
    }
  }

  public static GameMap createGameMap( List<String> map) {
    FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
        new GustOfWind(), new Graveyard(), new PuddleOfWater(), new Cliff(), new SummonSign(), new Cage(),
        new Barrack());
    return new GameMap(groundFactory, map);
  }
}
