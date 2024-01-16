package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AOEAttackAction;
import game.utils.Status;

/**
 * Scimitar class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class Scimitar extends WeaponItem {

  /**
   * Constructor.
   */
  public Scimitar() {
    super("Scimitar", 's', 118, "hits", 88);
    this.addCapability(Status.HAS_SPECIAL_SKILL);
  }

  /**
   * Get AOEAttackAction for Scimitar
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
