package game.statuses;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Poison status effect
 * <p>
 * Author: Behnam Mozafari, Josh Ryan
 */
public class PoisonEffect extends StatusEffect{

  /**
   * Constructor.
   *
   * @param actor Actor effect is applied to
   */
  public PoisonEffect(Actor actor) {
    super(4, actor);
  }

  /**
   * Do status effect
   */
  @Override
  public String execute() {
    decrementTurns();
    int damage = 17;
    actor.hurt(damage);
    while (!actor.isConscious()) {
      actor.heal(1);
    }
    return actor + " is poisoned for " + damage + " damage.";
  }

  /**
   * Returns description of status
   *
   * @return string description of status
   */
  @Override
  public String description() {
    return "Poison";
  }
}
