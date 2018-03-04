package fr_42_avaj_launcher.craft_engine;

import fr_42_avaj_launcher.exception.UnknownWeatherException;
import fr_42_avaj_launcher.simulator.WeatherTower;
import fr_42_avaj_launcher.inout.Logger;

/****************************************
 * Created by snadaras                  *
 * snadaras@student.42.fr on 20/02/2018 *
 */

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.weatherTower = null;
    }

    public void updateConditions() throws UnknownWeatherException {
        String weather = this.weatherTower.getWeather(coordinates);
        Coordinates newCoordinates = null;

        if (weather.equals("SUN")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude() + 2,
                    coordinates.getLatitude(),
                    Math.min(coordinates.getHeight() + 4, 100)
            );
            Logger.getLogger().log(
                    "Baloon#" + name + "(" + id + "): Good sunny weather! let's climb the summet of mount & take some pictures"
            );

        } else if (weather.equals("RAIN")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    Math.max(coordinates.getHeight() - 5, 0)
            );
            Logger.getLogger().log(
                    "Baloon#" + name + "(" + id + "): Damn! It's the deluge! Jack, can you get out the buckets?"
            );

        } else if (weather.equals("FOG")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    Math.max(coordinates.getHeight() - 3, 0)
            );
            Logger.getLogger().log(
                    "Baloon#" + name + "(" + id + "):  Wtf? Can not see at 5 meters, gonna go quickly on safe area"
            );

        } else if (weather.equals("SNOW")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    Math.max(coordinates.getHeight() - 15, 0)
            );
            Logger.getLogger().log(
                    "Baloon#" + name + "(" + id + "): Strange climate! It's snowing now! If I had known I would have taken my hat and my coat"
            );

        } else {
            throw new UnknownWeatherException("Unknown weather: " + weather);
        }

        coordinates = newCoordinates;

        if (coordinates.getHeight() <= 0) {
            Logger.getLogger().log("Baloon#" + name + "(" + id + ") landing.");
            this.weatherTower.unregister(this);
            Logger.getLogger().log(
                    "Tower says: Baloon#" + name + "(" + id + ") unregistered from Weather Tower."
            );
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Logger.getLogger().log(
                "Tower says: Baloon#" + name + "(" + id + ") registered to Weather Tower."
        );
    }
}