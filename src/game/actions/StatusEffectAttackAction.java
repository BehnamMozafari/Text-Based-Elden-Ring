package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.statuses.StatusEffect;
import game.statuses.StatusManager;
import game.utils.Utils;

/**
 * Attack action to apply status effects
 * <p>
 * Author: Behnam Mozafari
 */
public class StatusEffectAttackAction extends Action {

  /**
   * The Actor that is to be attacked
   */
  private Actor target;

  /**
   * The direction of incoming attack.
   */
  private String direction;

  /**
   * Weapon used for the attack
   */
  private Weapon weapon;

  /**
   * Status effect to apply
   */
  private StatusEffect statusEffect;

  /**
   * Constructor.
   *
   * @param target       the Actor to attack
   * @param direction    the direction where the attack should be performed (only used for display
   *                     purposes)
   * @param weapon       Weapon to attack with
   * @param statusEffect status effect to apply
   */
  public StatusEffectAttackAction(Actor target, String direction, Weapon weapon,
      StatusEffect statusEffect) {
    this.target = target;
    this.direction = direction;
    this.weapon = weapon;
    this.statusEffect = statusEffect;
  }

  /**
   * Perform the Action.
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return a description of what happened that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    if (!(Utils.percentageCheck(weapon.chanceToHit()))) {
      return actor + " misses " + target + ". " + statusEffect.description() + " wasn't applied.";
    }

    int damage = weapon.damage();
    String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
    target.hurt(damage);
    if (Utils.percentageCheck(50)) {
      StatusManager statusManager = StatusManager.getInstance();
      statusManager.addStatus(target, statusEffect);
      result += " " + statusEffect.description() + " was applied.";
    } else {
      result += " " + statusEffect.description() + " wasn't applied.";
    }
    if (!target.isConscious()) {
      result += new DeathAction(actor).execute(target, map);
    }
    return result;
  }

  /**
   * Returns a descriptive string
   *
   * @param actor The actor performing the action.
   * @return the text we put on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " attacks " + target + " at " + direction + " with " + weapon + "'s "
        + statusEffect.description();
  }
}
