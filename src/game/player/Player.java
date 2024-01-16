package game.player;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RespawnAction;
import game.combatants.CombatantType;
import game.items.RuneManager;
import game.items.Runes;
import game.items.remeberances.RemembranceOfTheGrafted;
import game.resethandling.ResetManager;
import game.statuses.StatusEffect;
import game.statuses.StatusManager;
import game.utils.Status;
import game.items.Flask;
import game.resethandling.Resettable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * @author Adrian Kristanto
 * @author Gunnraj Dhaliwal
 *
 */
public class Player extends Actor implements Resettable {

	/**
	 * menu object that displays during player's turn
	 */
	private final Menu menu = new Menu();
	/**
	 * last rested location
	 */
	private Location lastRested;
	/**
	 * The last location the player was in
	 */
	private Location lastLocation;
	/**
	 * The archetype of the player
	 */
	private Archetype archetype;
	/**
	 * A flask item
	 */
	private Flask flask;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param archetype   Player's combat archetype
	 */
	public Player(String name, char displayChar, Archetype archetype) {
		super(name, displayChar, archetype.hitPoints());

		// allows player to be targeted for enemy attacks
		this.addCapability(Status.HOSTILE_TO_ENEMY);

		// important for areas that enemies cannot enter
		this.addCapability(CombatantType.FRIENDLY);

		// important for player specific actions such as trading
		this.addCapability(Status.IS_PLAYER);

		// allows player to be followed by enemies
		this.addCapability(Status.FOLLOWED_BY_ENEMY);

		// allows for the character to fall of cliffs
		this.addCapability(Status.CAN_FALL_OFF_CLIFF);


		this.addWeaponToInventory(archetype.startingWeapon());
		this.archetype = archetype;
		this.flask = new Flask();
		this.addItemToInventory(this.flask);
		this.addItemToInventory(new RemembranceOfTheGrafted());

		// runes are stored in both the player inventory and the RuneManager
		Runes runes = new Runes(0);
		this.addItemToInventory(runes);
		RuneManager.getInstance().registerActorRunes(this, runes);
	}

	/**
	 * The turn of the player, allowing them to choose what action they wish to do. If they have died, they will only be prompted to respawn
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the player's chosen action
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// if the player needs to be reset, they should be respawned this turn only
		if (hasCapability(Status.WAS_KILLED)) {
			removeCapability(Status.WAS_KILLED);
			ActionList respawnAction = new ActionList();
			respawnAction.add(new RespawnAction(this, lastRested));
			return menu.showMenu(this, respawnAction, display);
		}
		StatusManager statusManager = StatusManager.getInstance();
		List<StatusEffect> statuses = statusManager.getStatuses(this);
		List<StatusEffect> statusesTemp = new ArrayList<StatusEffect>();
		if (statuses != null && statuses.size() > 0) {
			for (StatusEffect status : new ArrayList<StatusEffect>(statuses)) {
				new Display().println(status.execute());
				if (status.getTurns() >= 0) {
					statusesTemp.add(status);
				}
			}
			statusManager.setStatuses(this, statusesTemp);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}

		// Update last location of player
		lastLocation = map.locationOf(this);

		new Display().println("Tarnished's current health is " + this.hitPoints);
		new Display().println("Tarnished has " + RuneManager.getInstance().getActorRunes(this).getAmount() + " runes");
		if (this.hasCapability(Status.IMMOBILE)) {
			actions = new ActionList(new DoNothingAction());
		}
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * The reset method of the player.
	 */
	@Override
	public void reset() {
		this.flask.resetUses();
		this.hitPoints = getMaxHp();
		ResetManager.getInstance().registerResettable(this);
	}

	/**
	 * Sets the last rested location
	 * @param lastRested the last rested location
	 */
	public void setLastRested(Location lastRested) {
		this.lastRested = lastRested;
	}


	/**
	 * Gets the last location of the player
	 * @return the last location of the player
	 */
	public Location getLastLocation() {
		return lastLocation;
	}
}
