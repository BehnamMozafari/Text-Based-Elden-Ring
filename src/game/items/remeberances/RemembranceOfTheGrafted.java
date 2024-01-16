package game.items.remeberances;

import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * Rememberance of the Grafted. Should be dropped by Godrick the Grafted.
 * Can be traded for the Axe of Godrick or the Grafted Dragon
 *
 * @author Gunnraj Dhaliwal
 */
public class RemembranceOfTheGrafted extends Rememberances {
    /**
     * Constructor
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O');
        super.tradeableWeapons.add(new AxeOfGodrick());
        super.tradeableWeapons.add(new GraftedDragon());
    }
}
