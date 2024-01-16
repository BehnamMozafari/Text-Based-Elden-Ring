package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.QuickstepAction;

/**
 * A simple weapon that can be used to attack the enemy. It deals 75 damage with 70% hit rate It can
 * use the special Quickstep skill. Created by:
 *
 * @author Josh Ryan
 */
public class GreatKnife extends WeaponItem {

  /**
   * Constructor.
   */
  public GreatKnife() {
    super("Great Knife", '/', 75, "stabs", 70);
  }

  /**
   * Gets the special skill of the item, which is the quickstep action.
   *
   * @return an action representing the special quickstep skill.
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    return new QuickstepAction(target, direction, this);
  }
}
