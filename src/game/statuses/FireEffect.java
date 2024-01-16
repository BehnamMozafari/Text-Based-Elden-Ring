package game.statuses;

import edu.monash.fit2099.engine.actors.Actor;
import game.utils.Status;

/**
 * Fire status effect
 * <p>
 * Author: Behnam Mozafari, Josh Ryan
 */
public class FireEffect extends StatusEffect {

  /**
   * Constructor.
   *
   * @param actor Actor effect is applied to
   */
  public FireEffect(Actor actor) {
    super(1, actor);
  }

  /**
   * Do status effect
   */
  @Override
  public String execute() {
    decrementTurns();
    if (actor.hasCapability(Status.IMMUNE_TO_FIRE)) {
      return "";
    }
    int damage = 40;
    actor.hurt(damage);
    while (!actor.isConscious()) {
      actor.heal(1);
    }
    return actor + " is on fire for " + damage + " damage.";
  }

  /**
   * Returns description of status
   *
   * @return string description of status
   */
  @Override
  public String description() {
    return "Fire";
  }
}
