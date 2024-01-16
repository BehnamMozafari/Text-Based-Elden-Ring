package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AOEAttackAction;
import game.utils.Status;

/**
 * Slam class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class Slam extends WeaponItem {

  /**
   * Constructor.
   *
   * @param damage  amount of damage this weapon does
   * @param hitRate the probability/chance to hit the target.
   */
  public Slam(int damage, int hitRate) {
    super("Slam", ' ', damage, "slams", hitRate);
    this.togglePortability();
    this.addCapability(Status.HAS_SPECIAL_SKILL);
  }

  /**
   * Get AOEAttackAction for Slam
   *
   * @param target    target actor
   * @param direction direction for special skill
   * @return AOEAttackAction action
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    return new AOEAttackAction(this);
  }
}
