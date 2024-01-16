package game.combatants.friendlies;


import game.combatants.CombatantType;
import game.combatants.PlayerLike;
import game.player.Archetype;
import game.resethandling.Resettable;

/**
 * Ally class
 *
 * @author Josh Ryan
 * @version 1.0
 */
public class Ally extends PlayerLike implements Resettable {

    /**
     * Constructor.
     *
     * @param archetype   the Ally's archetype
     */
    public Ally(Archetype archetype) {
        super("Ally", 'A', archetype, 0, 0, 0, false);
        this.addCapability(CombatantType.FRIENDLY);
    }
}
