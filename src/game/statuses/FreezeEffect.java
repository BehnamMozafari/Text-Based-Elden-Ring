package game.statuses;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.DeathAction;
import game.utils.Status;

/**
 * Freeze status effect
 * <p>
 * Author: Behnam Mozafari, Josh Ryan
 */
public class FreezeEffect extends StatusEffect{

  /**
   * Constructor.
   *
   * @param actor Actor effect is applied to
   */
  public FreezeEffect(Actor actor) {
    super(2, actor);
  }

  /**
   * Do status effect
   */
  @Override
  public String execute() {
    if (decrementTurns()) {
      actor.removeCapability(Status.IMMOBILE);
      return actor + " thawed out.";
    }
    int damage = 15;
    actor.hurt(damage);
    while (!actor.isConscious()) {
      actor.heal(1);
    }
    actor.addCapability(Status.IMMOBILE);
    return actor + " is frozen for " + damage + " damage.";
  }

  /**
   * Returns description of status
   *
   * @return string description of status
   */
  @Override
  public String description() {
    return "Freeze";
  }
}
