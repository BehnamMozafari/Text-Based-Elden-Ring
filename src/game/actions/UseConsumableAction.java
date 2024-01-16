package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

public class UseConsumableAction extends Action {
    Consumable consumable;

    public UseConsumableAction(Consumable consumable) {
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.use(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return consumable.getDescription(actor);
    }
}
