package fr_42_avaj_launcher.craft_engine;

import fr_42_avaj_launcher.exception.UnknownWeatherException;
import fr_42_avaj_launcher.tower.WeatherTower;

/**
 * Created by snadaras on 20/02/2018.
 */

public interface Flyable {

    void updateConditions() throws UnknownWeatherException;

    void registerTower(WeatherTower weatherTower);
}
