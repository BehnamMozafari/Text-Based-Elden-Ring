package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.combatants.CombatantType;

/**
 * A class that represents the floor inside a building. Created by:
 *
 * @author Riordan D. Alfredo Modified by:
 */
public class Floor extends Ground {

  public Floor() {
    super('_');
  }

  @Override
  public boolean canActorEnter(Actor actor) {
    return actor.hasCapability(CombatantType.FRIENDLY);
  }
}
