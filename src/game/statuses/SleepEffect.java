package game.statuses;

import edu.monash.fit2099.engine.actors.Actor;
import game.utils.Status;

/**
 * Sleep status effect
 * <p>
 * Author: Behnam Mozafari, Josh Ryan
 */
public class SleepEffect extends StatusEffect{

  /**
   * Constructor.
   *
   * @param actor Actor effect is applied to
   */
  public SleepEffect(Actor actor) {
    super(3, actor);
  }

  /**
   * Do status effect
   */
  @Override
  public String execute() {
    if (decrementTurns()) {
      actor.removeCapability(Status.IMMOBILE);
      return actor + " woke up.";
    }
    actor.addCapability(Status.IMMOBILE);
    return actor + " is asleep.";
  }

  /**
   * Returns description of status
   *
   * @return string description of status
   */
  @Override
  public String description() {
    return "Sleep";
  }
}
