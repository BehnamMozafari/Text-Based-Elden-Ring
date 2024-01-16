package game.combatants.enemies;

import game.combatants.CombatantType;
import game.combatants.PlayerLike;
import game.player.Archetype;

/**
 * Invader class
 *
 * @author Josh Ryan
 * @version 1.0
 */
public class Invader extends PlayerLike {

    /**
     * Constructor.
     *
     * @param archetype   the Invader's archetype
     */
    public Invader(Archetype archetype) {
        // couldn't use character from requirement, caused error generating JavaDocs
        super("Invader", '9', archetype, 0, 1358, 5578, true);
        this.addCapability(CombatantType.INVADER);
    }
}
