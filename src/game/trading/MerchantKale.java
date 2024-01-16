package game.trading;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.Runes;
import game.weapons.*;

/**
 * Merchant Kale, who is an extension of the Trader abstract class.
 * @author Gunnraj Dhaliwal
 * @see Actor
 */
public class MerchantKale extends Trader {

  /**
   * Constructor
   */
  public MerchantKale() {
    super("Merchant Kale", 'K');

    super.addWeaponWantsToBuy(new Uchigatana(), new Runes(500));
    super.addWeaponWantsToBuy(new GreatKnife(), new Runes(350));
    super.addWeaponWantsToBuy(new Club(), new Runes(100));
    super.addWeaponWantsToBuy(new Grossmesser(), new Runes(100));

    super.addWeaponWantsToSell(new Uchigatana(), new Runes(5000));
    super.addWeaponWantsToSell(new GreatKnife(), new Runes(3500));
    super.addWeaponWantsToSell(new Club(), new Runes(600));
    super.addWeaponWantsToSell(new BladeOfFrost(), new Runes(5000));
    super.addWeaponWantsToSell(new MoltenHammer(), new Runes(5500));
    super.addWeaponWantsToSell(new PoisonDagger(), new Runes(4000));
    super.addWeaponWantsToSell(new SlumberAxe(), new Runes(6000));
    }
}
