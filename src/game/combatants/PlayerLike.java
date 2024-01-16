package game.combatants;

import game.player.Archetype;

/**
 * PlayerLike abstract class
 *
 * @author Josh ryan
 * @version 1.0
 */
public abstract class PlayerLike extends Combatant implements DropsRunes {

    /**
     * Archetype for PlayerLike combatant
     */
    private Archetype archetype;

    /**
     * Constructor.
     *
     * @param name          the name of the Actor
     * @param displayChar   the character that will represent the Actor in the display
     * @param archetype     the Actor's archetype
     * @param despawnChance the Actor's despawn chance
     * @param minRunes the minimum runes this PlayerLike actor can drop
     * @param maxRunes the maximum runes this PlayerLike actor can drop
     * @param enemy defines whether the PlayerLike combatant is an enemy or not
     */
    public PlayerLike(String name, char displayChar, Archetype archetype, int despawnChance, int minRunes, int maxRunes, boolean enemy) {
        super(name, displayChar, archetype.hitPoints(), despawnChance, enemy);
        this.archetype = archetype;
        this.addWeaponToInventory(archetype.startingWeapon());
        this.addCombatantAttackBehaviour(this.getWeaponInventory().get(0));
        runeGenerator(this, minRunes, maxRunes);
    }
}
