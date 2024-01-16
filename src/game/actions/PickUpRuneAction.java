package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.RuneManager;
import game.items.Runes;

/**
 * An action that causes runes to be picked up
 *
 * @author Gunnraj Dhaliwal
 */
public class PickUpRuneAction extends Action {

  /**
   * runes to be picked up
   */
  Runes runes;

  /**
   * Constructor.
   *
   * @param runes the runes to pick up
   */
  public PickUpRuneAction(Runes runes) {
    this.runes = runes;
  }

  /**
   * When executed, the runes on the floor will be picked up and added to the actor's rune table in
   * the rune manager.
   *
   * @param actor the actor picking up the runes.
   * @param map   the map the actor is on.
   * @return the result of the runes being picked up.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    // removing runes from map and adding to actor's runes who picked them up
    map.locationOf(actor).removeItem(runes);
    RuneManager.getInstance().getActorRunes(actor).addAmount(runes.getAmount());

    // if we have picked up the player's old runes, then we remove them from RuneManager
    if (runes.IsPlayerRunes()) {
      RuneManager.getInstance().setDroppedFromPlayerDeathRunes(null);
    }

    runes.togglePortability();

    return menuDescription(actor);
  }

  /**
   * Describes which target the actor is attacking with which weapon and with the quickstep
   * ability.
   *
   * @param actor the actor picking up the runes.
   * @return a description used for the menu UI
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " picks up " + runes.getAmount() + " runes";
  }
}
