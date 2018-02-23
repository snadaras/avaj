package fr_42_avaj_launcher.craft_engine;

import fr_42_avaj_launcher.exception.UnknownWeatherException;
import fr_42_avaj_launcher.simulator.WeatherTower;

/****************************************
 * Created by snadaras                  *
 * snadaras@student.42.fr on 20/02/2018 *
 */

public interface Flyable {

    public void updateConditions() throws UnknownWeatherException;

    public void registerTower(WeatherTower weatherTower);
}
