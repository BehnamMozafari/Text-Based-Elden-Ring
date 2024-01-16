package game.trading;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.TradeAction;
import game.items.RuneManager;
import game.items.Runes;
import game.combatants.CombatantType;
import game.utils.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Actor that can buy and sell weapons to/from the player. Does not move or interact in any other
 * way with world
 *
 * @author Gunnraj Dhaliwal
 * @see Actor
 */
public abstract class Trader extends Actor {

  /**
   * hashmap that contains the weapons the Trader can sell to others and the associated prices
   */
  private HashMap<WeaponItem, ArrayList<Tradeable>> weaponsWantsToSell = new HashMap<>();
  /**
   * hashmap that contains the weapons the Trader can buy from others and the associated prices
   */
  private HashMap<WeaponItem, ArrayList<Tradeable>> weaponsWantsToBuy = new HashMap<>();
  /**
   * hashmap that contains the items the Trader can sell to others and the associated prices
   */
  private HashMap<Item, ArrayList<Tradeable>> itemsWantsToSell = new HashMap<>();
  /**
   * hashmap that contains the items the Trader can buy from others and the associated prices
   */
  private HashMap<Item, ArrayList<Tradeable>> itemsWantsToBuy = new HashMap<>();

  /**
   * Constructor
   *
   * @param name A string which represents the name of the Trader
   * @param character The character that will represent this actor
   */
  public Trader(String name, char character) {
    super(name, character, 100);
    this.addCapability(CombatantType.FRIENDLY);
    RuneManager.getInstance().registerActorRunes(this, new Runes(Integer.MAX_VALUE));
  }

  /**
   * Allows for the adding of new weapons and their associated price for the Trader to sell
   *
   * @param weapon The weapon the Trader can now sell
   * @param price  The price they will sell the weapon for
   */
  public void addWeaponWantsToSell(WeaponItem weapon, Tradeable price) {
    weaponsWantsToSell.computeIfAbsent(weapon, k -> new ArrayList<>());
    weaponsWantsToSell.get(weapon).add(price);
  }

  /**
   * Allows for the adding of new weapons and their associated price for the Trader to buy
   *
   * @param weapon The weapon the Trader can now buy
   * @param price  The price they will buy the weapon for
   */
  public void addWeaponWantsToBuy(WeaponItem weapon, Tradeable price) {
    weaponsWantsToBuy.computeIfAbsent(weapon, k -> new ArrayList<>());
    weaponsWantsToBuy.get(weapon).add(price);
  }

  /**
   * Allows for the adding of new items and their associated price for the Trader to sell
   *
   * @param item The item the Trader can now sell
   * @param price  The price they will sell the item for
   */
  public void addItemWantsToSell(Item item, Tradeable price) {
    itemsWantsToSell.computeIfAbsent(item, k -> new ArrayList<>());
    itemsWantsToSell.get(item).add(price);
  }

  /**
   * Allows for the adding of new items and their associated price for the Trader to buy
   *
   * @param item The item the Trader can now buy
   * @param price  The price they will buy the item for
   */
  public void addItemWantsToBuy(Item item, Tradeable price) {
    itemsWantsToBuy.computeIfAbsent(item, k -> new ArrayList<>());
    itemsWantsToBuy.get(item).add(price);
  }

  /**
   * If an actor is able to interact with the Trader, the trader will create all possible tradeActions
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return a list of actions that can be done to/with the actor
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();

    // ensuring interacting actor is player only
    if (!otherActor.hasCapability(Status.IS_PLAYER)) {
      return actions;
    }

    // Things that the Trader will buy from others
    getWeaponBuyActions(actions, otherActor);
    getItemBuyActions(actions, otherActor);

    // Things that the Trader will sell to others
    getWeaponSellActions(actions, otherActor);
    getItemSellActions(actions, otherActor);

    // Ensures they can buy anything
    RuneManager.getInstance().addToActorRunes(this, Integer.MAX_VALUE);

    return actions;
  }

  /**
   * This method will create all possible weapon sell actions (Trader selling to other actor)
   *
   * @param actions A list of actions that the sell actions will be added to
   * @param buyingActor The actor buying the Trader's goods
   */
  private void getWeaponSellActions(ActionList actions, Actor buyingActor) {
    for (Map.Entry<WeaponItem, ArrayList<Tradeable>> entry : weaponsWantsToSell.entrySet()) {
      TradeableWeaponWrapper weaponToSell = new TradeableWeaponWrapper(entry.getKey());

      for (Tradeable tradeable : entry.getValue()) {
        actions.add(new TradeAction(this, buyingActor, weaponToSell, tradeable));
      }

    }
  }

  /**
   * This method will create all possible item sell actions (Trader selling to other actor)
   *
   * @param actions A list of actions that the sell actions will be added to
   * @param buyingActor The actor buying the Trader's goods
   */
  private void getItemSellActions(ActionList actions, Actor buyingActor) {
    for (Map.Entry<Item, ArrayList<Tradeable>> entry : itemsWantsToSell.entrySet()) {
      TradeableItemWrapper itemToSell = new TradeableItemWrapper(entry.getKey());

      for (Tradeable tradeable : entry.getValue()) {
        actions.add(new TradeAction(this, buyingActor, itemToSell, tradeable));
      }
    }
  }

  /**
   * Creates the weapon trade actions that the trader will buy from the player
   *
   * @param actions A list of actions that the sell actions will be added to
   * @param sellingActor The actor buying the Trader's goods
   */
  private void getWeaponBuyActions(ActionList actions, Actor sellingActor) {

    // getting selling actor's total inventory
    List<WeaponItem> sellingActorWeaponInventory = sellingActor.getWeaponInventory();

    // looping over the Trader's list of weapons they are willing to buy
    for (Map.Entry<WeaponItem, ArrayList<Tradeable>> entry : weaponsWantsToBuy.entrySet()) {
      WeaponItem weaponToBuy = entry.getKey();

      // looping over player's inventory
      for (WeaponItem sellingActorWeapon : sellingActorWeaponInventory) {

        // comparing if weapons are the same type
        if (weaponToBuy.getClass().equals(sellingActorWeapon.getClass())) {
          TradeableWeaponWrapper purchasableWeapon = new TradeableWeaponWrapper(sellingActorWeapon);

          // creating all sell action for valid weapons
          for (Tradeable tradeable : entry.getValue()) {
            actions.add(new TradeAction(sellingActor, this, purchasableWeapon, tradeable));
          }

        }

      }

    }

  }

  /**
   * Creates the item trade actions that the trader will buy from the player
   *
   * @param actions A list of actions that the sell actions will be added to
   * @param sellingActor The actor buying the Trader's goods
   */
  private void getItemBuyActions(ActionList actions, Actor sellingActor) {

    // getting selling actor's total inventory
    List<Item> sellingActorItemInventory = sellingActor.getItemInventory();

    // looping over the Trader's list of weapons they are willing to buy
    for (Map.Entry<Item, ArrayList<Tradeable>> entry : itemsWantsToBuy.entrySet()) {
      Item itemToBuy = entry.getKey();

      // looping over player's inventory
      for (Item sellingActorItem : sellingActorItemInventory) {

        // comparing if weapons are the same type
        if (itemToBuy.getClass().equals(sellingActorItem.getClass())) {
          TradeableItemWrapper purchasableItem = new TradeableItemWrapper(sellingActorItem);

          // creating all sell action for valid weapons
          for (Tradeable tradeable : entry.getValue()) {
            actions.add(new TradeAction(sellingActor, this, purchasableItem, tradeable));
          }

        }

      }

    }

  }

  /**
   * The merchant does not do any actions and so does not do anything in this method
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in
   *                   conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return DoNothingAction
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
