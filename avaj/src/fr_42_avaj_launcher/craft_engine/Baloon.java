package fr_42_avaj_launcher.craft_engine;

import fr_42_avaj_launcher.exception.UnknownWeatherException;
import fr_42_avaj_launcher.tower.WeatherTower;
import fr_42_avaj_launcher.launch.Logger;

/**
 * Created by snadaras on 20/02/2018.
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
                    "Baloon#" + super.name + "(" + super.id + "): Let's enjoy the good weather and take some pics."
            );

        } else if (weather.equals("RAIN")) {
            newCoordinates = new Coordinates(
                    super.coordinates.getLongitude(),
                    super.coordinates.getLatitude(),
                    Math.max(super.coordinates.getHeight() - 5, 0)
            );
            Logger.getLogger().log(
                    "Baloon#" + super.name + "(" + super.id + "): Damn you rain! You messed up my baloon."
            );

        } else if (weather.equals("FOG")) {
            newCoordinates = new Coordinates(
                    super.coordinates.getLongitude(),
                    super.coordinates.getLatitude(),
                    Math.max(super.coordinates.getHeight() - 3, 0)
            );
            Logger.getLogger().log(
                    "Baloon#" + super.name + "(" + super.id + "): Oh, no! Why is it so foggy?"
            );

        } else if (weather.equals("SNOW")) {
            newCoordinates = new Coordinates(
                    super.coordinates.getLongitude(),
                    super.coordinates.getLatitude(),
                    super.coordinates.getHeight() - 15
            );
            Logger.getLogger().log(
                    "Baloon#" + super.name + "(" + super.id + "): It's snowing. We're gonna crash."
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
