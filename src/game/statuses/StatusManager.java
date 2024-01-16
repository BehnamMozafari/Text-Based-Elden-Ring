package game.statuses;
import edu.monash.fit2099.engine.actors.Actor;
import game.resethandling.ResetManager;
import game.resethandling.Resettable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatusManager implements Resettable {
    HashMap<Actor, List<StatusEffect>> statusMap = new HashMap<>();
    static StatusManager statusManager;

    private StatusManager() {
        ResetManager.getInstance().registerResettable(this);
    }

    public static StatusManager getInstance() {
        if (statusManager == null) {
            statusManager = new StatusManager();
        }
        return statusManager;
    }

    public List<StatusEffect> getStatuses(Actor actor) {
        if (statusMap.containsKey(actor)) {
            return statusMap.get(actor);
        }
        return null;
    }

    public void addStatus(Actor actor, StatusEffect statusEffect) {
        if (!statusMap.containsKey(actor)) {
            statusMap.put(actor, new ArrayList<>());
        }
        statusMap.get(actor).add(statusEffect);
    }

    public void setStatuses(Actor actor, List<StatusEffect> list) {
        statusMap.put(actor,list);
    }
    public void removeStatus(Actor actor, StatusEffect statusEffect) {
        statusMap.get(actor).remove(statusEffect);
    }

    public void clearStatus(Actor actor) {
        statusMap.remove(actor);
    }

    @Override
    public void reset() {
        statusMap.clear();
        ResetManager.getInstance().registerResettable(this);
    }
}
