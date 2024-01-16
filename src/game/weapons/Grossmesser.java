package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AOEAttackAction;
import game.utils.Status;

/**
 * Grossmesser class
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class Grossmesser extends WeaponItem {

  /**
   * Constructor.
   */
  public Grossmesser() {
    super("Grossmesser", '?', 115, "hits", 85);
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
