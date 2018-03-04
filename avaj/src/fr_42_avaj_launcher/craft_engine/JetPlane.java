package fr_42_avaj_launcher.craft_engine;

import fr_42_avaj_launcher.exception.UnknownWeatherException;
import fr_42_avaj_launcher.simulator.WeatherTower;
import fr_42_avaj_launcher.inout.Logger;

/****************************************
 * Created by snadaras                  *
 * snadaras@student.42.fr on 20/02/2018 *
 */

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
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
                    "JetPlane#" + name + "(" + id + "): Marleen? Where are you put my Sunglasses and UV protect cream?"
            );

        } else if (weather.equals("RAIN")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude() + 5,
                    coordinates.getHeight()
            );
            Logger.getLogger().log(
                    "JetPlane#" + name + "(" + id + "): It's raining today. Hope we won't met any duck front this way!"
            );

        } else if (weather.equals("FOG")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude() + 1,
                    coordinates.getHeight()
            );
            Logger.getLogger().log(
                    "JetPlane#" + name + "(" + id + "): This fog is real mashed peas! Can't see everythng, so are there others cuckoos in the area?"
            );

        } else if (weather.equals("SNOW")) {
            newCoordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    Math.max(coordinates.getHeight() - 7, 0)
            );
            Logger.getLogger().log(
                    "JetPlane#" + name + "(" + id + "): OMG! Guys, I won't replay you the GOT! Winter is coming!"
            );
        } else {
            throw new UnknownWeatherException("Unknown weather: " + weather);
        }

        super.coordinates = newCoordinates;

        if (super.coordinates.getHeight() <= 0) {
            Logger.getLogger().log("JetPlane#" + name + "(" + id + ") landing.");
            this.weatherTower.unregister(this);
            Logger.getLogger().log(
                    "Tower says: JetPlane#" + name + "(" + id + ") unregistered from Weather Tower."
            );
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Logger.getLogger().log(
                "Tower says: JetPlane#" + name + "(" + id + ") registered to Weather Tower."
        );
    }
}