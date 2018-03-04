package fr_42_avaj_launcher.craft_engine;

import fr_42_avaj_launcher.exception.UnknownWeatherException;
import fr_42_avaj_launcher.simulator.WeatherTower;
import fr_42_avaj_launcher.inout.Logger;

/****************************************
 * Created by snadaras                  *
 * snadaras@student.42.fr on 20/02/2018 *
 */

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.weatherTower = null;
    }

    public void updateConditions() throws UnknownWeatherException {
        String weather = this.weatherTower.getWeather(coordinates);
        Coordinates newCoordinates = null;

        if (weather.equals("SUN")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude() + 10,
                    coordinates.getLatitude(),
                    Math.min(coordinates.getHeight() + 2, 100)
            );
            Logger.getLogger().log(
                    "Helicopter#" + name + "(" + id + "): This is strongly hot! we need a real air fan inside."
            );

        } else if (weather.equals("RAIN")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude() + 5,
                    coordinates.getLatitude(),
                    coordinates.getHeight()
            );
            Logger.getLogger().log(
                    "Helicopter#" + name + "(" + id + "): It's so cool!! won't have to water my garden when I will back at home."
            );

        } else if (weather.equals("FOG")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude() + 1,
                    coordinates.getLatitude(),
                    coordinates.getHeight()
            );
            Logger.getLogger().log(
                    "Helicopter#" + name + "(" + id + "): Jesus! Are we yet in london? Hope, we won't have to check time from the Big ben"
            );

        } else if (weather.equals("SNOW")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    Math.max(coordinates.getHeight() - 12, 0)
            );
            Logger.getLogger().log(
                    "Helicopter#" + name + "(" + id + "): My rotor is going to freeze! Hey, Steevy! it's time to turn back."
            );

        } else {
            throw new UnknownWeatherException("Unknown weather: " + weather);
        }

        coordinates = newCoordinates;

        if (coordinates.getHeight() <= 0) {
            Logger.getLogger().log("Helicopter#" + name + "(" + id + ") landing.");
            this.weatherTower.unregister(this);
            Logger.getLogger().log(
                    "Tower says: Helicopter#" + name + "(" + id + ") unregistered from Weather Tower."
            );
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Logger.getLogger().log(
                "Tower says: Helicopter#" + name + "(" + id + ") registered to Weather Tower."
        );
    }
}
