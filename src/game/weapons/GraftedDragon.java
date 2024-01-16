package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class GraftedDragon extends WeaponItem {
    /**
     * Constructor
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "slams", 90);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
    }
}
