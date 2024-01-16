package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.StatusEffectAttackAction;
import game.statuses.FireEffect;
import game.statuses.SleepEffect;
import game.statuses.StatusEffect;

/**
 * A simple weapon that can be used to attack the enemy. It deals 200 damage with 35% hit rate and has a chance to apply
 * fire to the target.
 * Created by:
 *
 * @author Josh Ryan
 */
public class MoltenHammer extends WeaponItem {

  /**
   * Constructor.
   */
  public MoltenHammer() {
    super("Molten Hammer", 'F', 200, "smashes", 35);
  }

  /**
   * Gets the special skill of the item, which is a StatusEffectAttackAction.
   *
   * @return a StatusEffectAttackAction.
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    StatusEffect effect = new FireEffect(target);
    return new StatusEffectAttackAction(target, direction, this, effect);
  }
}
