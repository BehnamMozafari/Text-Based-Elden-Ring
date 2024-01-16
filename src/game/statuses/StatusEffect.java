package game.statuses;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Abstract class for status effects (e.g. sleep, poison, etc.)
 * <p>
 * Author: Behnam Mozafari, Josh Ryan
 */
public abstract class StatusEffect {

  /**
   * Number of turns left
   */
  private int turns;

  /**
   * Actor effect is applied to
   */
  protected Actor actor;

  /**
   * Constructor.
   *
   * @param turns number of turns to keep effect for
   * @param actor Actor effect is applied to
   */
  public StatusEffect(int turns, Actor actor) {
    this.turns = turns;
    this.actor = actor;
  }

  /**
   * Do status effect
   */
  public abstract String execute();

  /**
   * getter for turns
   *
   * @return number of turns
   */
  public int getTurns() {
    return turns;
  }

  /**
   * decrements turns by 1, returns true if turns is less than 0
   *
   * @return true if turns is negative, false otherwise
   */
  protected boolean decrementTurns() {
    this.turns -= 1;
    if (turns < 0) {
      StatusManager statusManager = StatusManager.getInstance();
      statusManager.removeStatus(actor, this);
      return true;
    }
    return false;
  }

  /**
   * Returns description of status
   *
   * @return string description of status
   */
  public abstract String description();
}
