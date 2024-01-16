package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.UnsheatheAction;

/**
 * A simple weapon that can be used to attack the enemy. It deals 115 damage with 80% hit rate It
 * can use the special Unsheathe skill. Created by:
 *
 * @author Josh Ryan
 */
public class Uchigatana extends WeaponItem {

  /**
   * Constructor.
   */
  public Uchigatana() {
    super("Uchigatana", ')', 115, "slashes", 80);
  }

  /**
   * Gets the special skill of the item, which is the unsheathe action.
   *
   * @return an action representing the special unsheathe skill.
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    return new UnsheatheAction(target, direction, this);
  }
}
