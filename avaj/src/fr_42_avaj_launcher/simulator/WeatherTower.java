package fr_42_avaj_launcher.simulator;

import fr_42_avaj_launcher.exception.UnknownWeatherException;
import fr_42_avaj_launcher.craft_engine.Coordinates;
import fr_42_avaj_launcher.weather.WeatherProvider;

/**
 * Created by snadaras on 20/02/2018.
 */
public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

     void changeWeather() throws UnknownWeatherException {
        WeatherProvider.getProvider().changeWeather();
        super.conditionsChanged();
    }
}