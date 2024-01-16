package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.StatusEffectAttackAction;
import game.actions.UnsheatheAction;
import game.statuses.PoisonEffect;
import game.statuses.SleepEffect;
import game.statuses.StatusEffect;

/**
 * A simple weapon that can be used to attack the enemy. It deals 80 damage with 80% hit rate and has a chance to apply
 * poison to the target.
 * Created by:
 *
 * @author Josh Ryan
 */
public class PoisonDagger extends WeaponItem {

  /**
   * Constructor.
   */
  public PoisonDagger() {
    super("Poison Dagger", '{', 80, "stabs", 80);
  }

  /**
   * Gets the special skill of the item, which is a StatusEffectAttackAction.
   *
   * @return a StatusEffectAttackAction.
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    StatusEffect effect = new PoisonEffect(target);
    return new StatusEffectAttackAction(target, direction, this, effect);
  }
}
