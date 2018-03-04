package fr_42_avaj_launcher.craft_engine;

import fr_42_avaj_launcher.exception.UnknownAircraftTypeException;

/****************************************
 * Created by snadaras                  *
 * snadaras@student.42.fr on 20/02/2018 *
 */

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
            throws UnknownAircraftTypeException {
                Flyable flyable = null;

        if (type.equals("Baloon")) {
            flyable = new Baloon(name, new Coordinates(longitude, latitude, height));

        } else if (type.equals("JetPlane")) {
            flyable = new JetPlane(name, new Coordinates(longitude, latitude, height));

        } else if (type.equals("Helicopter")) {
            flyable = new Helicopter(name, new Coordinates(longitude, latitude, height));

        } else {
            flyable = null;

        }

                if (flyable == null) {
                    throw new UnknownAircraftTypeException("Unknown aircraft type: " + type);
                }

                return flyable;
    }
}