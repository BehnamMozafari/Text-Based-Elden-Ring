package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.combatants.enemies.PileOfBones;
import game.items.RuneManager;
import game.combatants.CombatantType;
import game.statuses.StatusManager;
import game.utils.Status;
import game.items.Runes;
import game.player.PlayerManager;
import game.resethandling.ResetManager;

/**
 * An action executed if an actor is killed.
 * @author Adrian Kristanto
 * @author Gunnraj Dhaliwal
 * @author Behnam Mozafari
 */
public class DeathAction extends Action {
    private Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was,
     * UNLESS the actor is the player, then playerDeath method is called
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        RuneManager runeManager = RuneManager.getInstance();

        // If skeleton is killed, spawn PileOfBones
        if (target.findCapabilitiesByType(CombatantType.class).get(0) == CombatantType.SKELETON) {
            Location locationToSpawn = map.locationOf(target);
            map.removeActor(target);
            PileOfBones pileOfBones = new PileOfBones(target);
            ResetManager.getInstance().registerResettable(pileOfBones);
            locationToSpawn.addActor(pileOfBones);
            StatusManager.getInstance().clearStatus(target);
            return result;
        }

        // Different events for player dying
        if (target.hasCapability(Status.IS_PLAYER)) {
            result = playerDeath(target);
            return result;
        }

        ActionList dropActions = new ActionList();

        // Drop all items
        for (Item item : target.getItemInventory()) {
            dropActions.add(item.getDropAction(target));
        }

        for (WeaponItem weapon : target.getWeaponInventory()) {
            dropActions.add(weapon.getDropAction(target));
        }

        for (Action drop : dropActions) {
            drop.execute(target, map);
        }

        // If attacker is player and target is enemy, transfer runes from enemy to player
        if (attacker.hasCapability(Status.IS_PLAYER) && (!target.findCapabilitiesByType(CombatantType.class).isEmpty())) {
            runeManager.transferRunes(target, attacker);
        }

        // de-registering from RuneManager as they are dead (precaution, can occur if enemy kills another enemy)
        RuneManager.getInstance().removeActorRunes(target);

        // Remove actor
        map.removeActor(target);
        result += System.lineSeparator() + menuDescription(target);
        return result;
    }


    /**
     * Causes the player to drop their runes and for the world to then be reset
     * @param playerWhoDied the player who died
     * @return description of the player dying
     */
    public String playerDeath(Actor playerWhoDied) {
        playerWhoDied.addCapability(Status.WAS_KILLED);

        // getting player's runes
        Runes playerRunes = RuneManager.getInstance().getActorRunes(playerWhoDied);

        // removing runes from actor inventory and runeManager table, and adding to RuneManager droppedPlayerDeathRunes
        RuneManager.getInstance().setDroppedFromPlayerDeathRunes(playerRunes);
        RuneManager.getInstance().removeActorRunes(playerWhoDied);
        playerWhoDied.removeItemFromInventory(playerRunes);

        // drops runes at last location of player
        playerRunes.togglePortability();
        PlayerManager.getInstance().getPlayerLastLocation().addItem(playerRunes);

        // replaces the player's runes
        Runes newPlayerRunes = new Runes(0);
        RuneManager.getInstance().registerActorRunes(playerWhoDied, newPlayerRunes);
        playerWhoDied.addItemToInventory(newPlayerRunes);

        // reset actors that should be reset on player death
        ResetManager.getInstance().deathRun();

        return System.lineSeparator() + "YOU DIED!\n";
    }

    /**
     * Describes the death of the actor as a string.
     *
     * @param actor The actor dying.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
