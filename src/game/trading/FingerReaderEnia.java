package game.trading;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Runes;
import game.items.remeberances.Rememberances;
import game.items.remeberances.RemembranceOfTheGrafted;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Grossmesser;
import game.weapons.Uchigatana;

import java.util.ArrayList;
import java.util.List;

public class FingerReaderEnia extends Trader{
    /**
     * The list of rememberances that Enia can trade for
     */
    List<Rememberances> tradeableRememberances= new ArrayList<>();

    /**
     * Constructor
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E');
        tradeableRememberances.add(new RemembranceOfTheGrafted());

        super.addWeaponWantsToBuy(new Uchigatana(), new Runes(500));
        super.addWeaponWantsToBuy(new GreatKnife(), new Runes(350));
        super.addWeaponWantsToBuy(new Club(), new Runes(100));
        super.addWeaponWantsToBuy(new Grossmesser(), new Runes(100));

        for (Rememberances rememberance : tradeableRememberances) {

            for (WeaponItem weapon : rememberance.weaponsCanBeTradedFor()) {
                TradeableWeaponWrapper tradeableWeapon = new TradeableWeaponWrapper(weapon);
                super.addItemWantsToBuy(rememberance, tradeableWeapon);
            }

            for (Item item : rememberance.itemsCanBeTradedFor()) {
                TradeableItemWrapper tradeableItem = new TradeableItemWrapper(item);
                super.addItemWantsToBuy(rememberance, tradeableItem);
            }
        }
    }
}
