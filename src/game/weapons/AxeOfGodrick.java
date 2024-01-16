package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class AxeOfGodrick extends WeaponItem {
    /**
     * Constructor
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 89, "slices", 84);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
    }
}
