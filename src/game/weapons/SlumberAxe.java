package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.StatusEffectAttackAction;
import game.statuses.SleepEffect;
import game.statuses.StatusEffect;

/**
 * A simple weapon that can be used to attack the enemy. It deals 160 damage with 50% hit rate and has a chance to apply
 * sleep to the target.
 * Created by:
 *
 * @author Josh Ryan
 */
public class SlumberAxe extends WeaponItem {

  /**
   * Constructor.
   */
  public SlumberAxe() {
    super("Slumber Axe", 'P', 160, "chops", 50);
  }

  /**
   * Gets the special skill of the item, which is a StatusEffectAttackAction.
   *
   * @return a StatusEffectAttackAction.
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    StatusEffect effect = new SleepEffect(target);
    return new StatusEffectAttackAction(target, direction, this, effect);
  }
}
