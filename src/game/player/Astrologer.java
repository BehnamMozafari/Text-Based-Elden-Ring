package game.player;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.GreatKnife;
import game.weapons.SlumberAxe;

/**
 * The Astrologer archetype class
 *
 * @author Josh Ryan
 */
public class Astrologer implements Archetype {

    /**
     * The amount of health a player with this archetype starts with
     *
     * @return 396 hit points
     */
    @Override
    public int hitPoints() {
        return 396;
    }

    /**
     * The starting weapon of this archetype
     *
     * @return a SlumberAxe
     */
    @Override
    public WeaponItem startingWeapon() {
        return new SlumberAxe();
    }

    /**
     * The name of the archetype
     *
     * @return "Astrologer" string
     */
    @Override
    public String toString() {
        return "Astrologer";
    }
}
