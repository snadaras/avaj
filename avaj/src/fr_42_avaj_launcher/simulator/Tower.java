package fr_42_avaj_launcher.simulator;

import fr_42_avaj_launcher.exception.UnknownWeatherException;
import fr_42_avaj_launcher.craft_engine.Flyable;

import java.util.ArrayList;
import java.util.List;

/****************************************
 * Created by snadaras                  *
 * snadaras@student.42.fr on 20/02/2018 *
 */

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
    }

    protected void conditionsChanged() throws UnknownWeatherException {
        List<Flyable> copyOfObservers = new ArrayList<Flyable>();
        for (Flyable flyable : observers) {
            copyOfObservers.add(flyable);
        }
        for (Flyable flyable : copyOfObservers) {
            flyable.updateConditions();
        }
    }
}
