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
        String weather = this.weatherTower.getWeather(super.coordinates);
        Coordinates newCoordinates = null;

        if (weather.equals("SUN")) {
            newCoordinates = new Coordinates(
                    super.coordinates.getLongitude() + 2,
                    super.coordinates.getLatitude(),
                    Math.min(super.coordinates.getHeight() + 4, 100)
            );
            Logger.getLogger().log(
                    "Baloon#" + super.name + "(" + super.id + "): Good weather for climb the summet of mount and take some pics"
            );

        } else if (weather.equals("RAIN")) {
            newCoordinates = new Coordinates(
                    super.coordinates.getLongitude(),
                    super.coordinates.getLatitude(),
                    Math.max(super.coordinates.getHeight() - 5, 0)
            );
            Logger.getLogger().log(
                    "Baloon#" + super.name + "(" + super.id + "): Damn! It's the deluge ! Jack, can you get out the buckets?"
            );

        } else if (weather.equals("FOG")) {
            newCoordinates = new Coordinates(
                    super.coordinates.getLongitude(),
                    super.coordinates.getLatitude(),
                    Math.max(super.coordinates.getHeight() - 3, 0)
            );
            Logger.getLogger().log(
                    "Baloon#" + super.name + "(" + super.id + "):  Wtf? Can not see at 5 meters, gonna go quickly on safe area"
            );

        } else if (weather.equals("SNOW")) {
            newCoordinates = new Coordinates(
                    super.coordinates.getLongitude(),
                    super.coordinates.getLatitude(),
                    Math.max(super.coordinates.getHeight() - 15, 0)
            );
            Logger.getLogger().log(
                    "Baloon#" + super.name + "(" + super.id + "): Strange meteo! It's snowing now. if I had known I would have taken my hat and my coat"
            );

        } else {
            throw new UnknownWeatherException("Unknown weather: " + weather);
        }

        super.coordinates = newCoordinates;

        if (super.coordinates.getHeight() == 0) {
            Logger.getLogger().log("Baloon#" + super.name + "(" + super.id + ") landing.");
            this.weatherTower.unregister(this);
            Logger.getLogger().log(
                    "Tower says: Baloon#" + super.name + "(" + super.id + ") unregistered from weather tower."
            );
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Logger.getLogger().log(
                "Tower says: Baloon#" + super.name + "(" + super.id + ") registered to weather tower."
        );
    }
}
