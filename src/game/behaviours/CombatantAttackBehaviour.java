package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.combatants.CombatantType;
import game.utils.Status;
import game.utils.Utils;
import java.util.List;

/**
 * Behaviour for Enemies to attack other actors.
 *
 * @author Behnam Mozafari
 * @version 1.0
 */
public class CombatantAttackBehaviour implements Behaviour {

  /**
   * Weapon used for the attack
   */
  private WeaponItem weapon;

  /**
   * Constructor.
   *
   * @param weapon weapon for attack
   */
  public CombatantAttackBehaviour(WeaponItem weapon) {
    this.weapon = weapon;
  }

  /**
   * Constructor with intrinsic weapon as default
   */
  public CombatantAttackBehaviour() {
  }

  /**
   * Returns an arbitrary attack that an enemy can do, there is a 50% chance of using the enemy's
   * weapon's special skill if it has one, else if the enemy has a weapon, it will attack with the
   * weapon, or if it doesn't have a weapon, it will attack with its intrinsic weapon.
   *
   * @param enemy the Actor acting
   * @param map   the GameMap containing the Actor
   * @return An attack that the enemy can do, or null if the enemy cannot attack any other actor.
   */
  @Override
  public Action getAction(Actor enemy, GameMap map) {
    Location currLocation = map.locationOf(enemy);
    List<Exit> exits = currLocation.getExits();
    CombatantType enemyType = enemy.findCapabilitiesByType(CombatantType.class).get(0);

    // loop through all possible targets until a valid target is found
    for (Exit exit : exits) {
      Actor possibleTarget = exit.getDestination().getActor();
      CombatantType targetEnemyType = null;
      if (possibleTarget != null) {
        try {
          targetEnemyType = possibleTarget.findCapabilitiesByType(CombatantType.class).get(0);
        } catch (IndexOutOfBoundsException ignored) {
        }
        // enemies cant attack enemies of the same type, and skeletons cant attack pile of bones
        if (targetEnemyType != enemyType && !(enemyType == CombatantType.SKELETON
            && targetEnemyType == CombatantType.PILEOFBONES)) {
          // if enemy has a weapon, try to choose between special skill and normal attack
          // (50-50 chance)
          if (weapon != null) {
            if (Utils.percentageCheck(50) && weapon.hasCapability(Status.HAS_SPECIAL_SKILL)) {
              return weapon.getSkill(possibleTarget, exit.getName());
            } else {
              return new AttackAction(possibleTarget, exit.getName(), weapon);
            }
          } else {
            return new AttackAction(possibleTarget, exit.getName());
          }
        }
      }
    }

    return null;
  }
}
