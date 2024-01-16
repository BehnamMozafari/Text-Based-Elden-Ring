package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.combatants.enemies.Invader;
import game.combatants.friendlies.Ally;
import game.player.*;
import game.resethandling.ResetManager;
import game.utils.Utils;

import java.util.ArrayList;

/**
 * The action for using summon sign. Spawns an ally or invader.
 * @author Josh Ryan
 */
public class SummonSignAction extends Action {

    /**
     * Location of Summon Sign
     */
    private Location location;

    /**
     * ArrayList containing archetypes for Allies/Invaders
     */
    private ArrayList<Archetype> archetypeList = new ArrayList<Archetype>();

    /**
     * Constructor.
     * @param location location of the Summon Sign
     */
    public SummonSignAction(Location location) {
        this.location = location;
        this.archetypeList.add(new Bandit());
        this.archetypeList.add(new Samurai());
        this.archetypeList.add(new Wretch());
        this.archetypeList.add(new Astrologer());
    }

    /**
     * executes the action - spawning an ally/invader
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string stating a succesful rest
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for(Exit exit : location.getExits()) {
            if (exit.getDestination().canActorEnter(actor)) {
                Archetype archetype = archetypeList.get(Utils.getRandomInt(4));
                if (Utils.percentageCheck(50)) {
                    Ally ally = new Ally(archetype);
                    ResetManager.getInstance().registerDeathResettable(ally);
                    exit.getDestination().addActor(ally);
                    return actor + " used the summon sign and it spawned an ally!";
                } else {
                    Invader invader = new Invader(archetype);
                    ResetManager.getInstance().registerDeathResettable(invader);
                    exit.getDestination().addActor(invader);
                    return actor + " used the summon sign and it spawned an invader!";
                }
            }
        }
        return actor + "used the summon sign, but there was valid place for ally/invader to be spawned!";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses the summon sign.";
    }
}
