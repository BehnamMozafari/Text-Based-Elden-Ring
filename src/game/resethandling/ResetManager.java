package game.resethandling;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * @author Adrian Kristanto
 * @author Gunnraj Dhaliwal
 */
public class ResetManager {

  /**
   * list of objects that need to be reset
   */
  private List<Resettable> resettables;
  /**
   * list of objects that need to be reset on player death
   */
  private List<Resettable> deathResettables;
  /**
   * used for singleton pattern
   */
  private static ResetManager instance;

  /**
   * Constructor
   */
  private ResetManager() {
    this.resettables = new ArrayList<>();
    this.deathResettables = new ArrayList<>();
  }

  /**
   * Used for singleton pattern to ensure only 1 ResetManager can exist at any time
   *
   * @return instance of ResetManager
   */
  public static ResetManager getInstance() {
    if (instance == null) {
      instance = new ResetManager();
    }
    return instance;
  }

  /**
   * Calls the reset method of every resettable object.
   */
  public void run() {
    List<Resettable> copy = new ArrayList<>(resettables);
    resettables.clear();
    for (Resettable resettable : copy) {
      resettable.reset();
    }
  }

  /**
   * Calls the reset method of every deathResettable object.
   */
  public void deathRun() {
    List<Resettable> copy = new ArrayList<>(deathResettables);
    deathResettables.clear();
    for (Resettable resettable : copy) {
      resettable.reset();
    }
    run();
  }

  /**
   * Adds a resettable object into the resettable list
   *
   * @param resettable a resettable object
   */
  public void registerResettable(Resettable resettable) {
    if (!this.resettables.contains(resettable)) {
      this.resettables.add(resettable);
    }
  }

  /**
   * Removes a resettable object from the resettable list
   *
   * @param resettable a resettable object
   */
  public void removeResettable(Resettable resettable) {
    this.resettables.remove(resettable);
  }

  /**
   * Adds a resettable object into the resettable list
   *
   * @param resettable a resettable object
   */
  public void registerDeathResettable(Resettable resettable) {
    if (!this.deathResettables.contains(resettable)) {
      this.deathResettables.add(resettable);
    }
  }

  /**
   * Removes a resettable object from the resettable list
   *
   * @param resettable a resettable object
   */
  public void removeDeathResettable(Resettable resettable) {
    this.deathResettables.remove(resettable);
  }
}