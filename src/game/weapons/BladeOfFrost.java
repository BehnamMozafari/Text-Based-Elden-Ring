package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.StatusEffectAttackAction;
import game.statuses.FreezeEffect;
import game.statuses.SleepEffect;
import game.statuses.StatusEffect;

/**
 * A simple weapon that can be used to attack the enemy. It deals 80 damage with 80% hit rate and has a chance to apply
 * free to the enemy.
 * Created by:
 *
 * @author Josh Ryan
 */
public class BladeOfFrost extends WeaponItem {

  /**
   * Constructor.
   */
  public BladeOfFrost() {
    super("Blade of Frost", '}', 110, "slashes", 85);
  }

  /**
   * Gets the special skill of the item, which is a StatusEffectAttackAction.
   *
   * @return a StatusEffectAttackAction.
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    StatusEffect effect = new FreezeEffect(target);
    return new StatusEffectAttackAction(target, direction, this, effect);
  }
}
