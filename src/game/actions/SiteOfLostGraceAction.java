package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.player.PlayerManager;
import game.resethandling.ResetManager;

/**
 * The action of resting at the site of grace. Should reset the world and set new spawn point for player
 * @author Josh Ryan
 * Modified by: Gunnraj Dhaliwal
 */
public class SiteOfLostGraceAction extends Action {

    /**
     * Constructor. Sets the player's spawn point
     * @param location location of the site of grace
     */
    public SiteOfLostGraceAction(Location location) {
        PlayerManager.getInstance().updatePlayerSpawnLocation(location);
    }

    /**
     * executes the action - resetting the world
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string stating a succesful rest
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // save location to player or reset manager as last rested location
        ResetManager.getInstance().run();
        return "Player rests on the Site of Lost Grace...";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at Site of Lost Grace and resets game.";
    }
}
