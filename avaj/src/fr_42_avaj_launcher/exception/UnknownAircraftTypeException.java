package fr_42_avaj_launcher.exception;

/****************************************
 * Created by snadaras                  *
 * snadaras@student.42.fr on 20/02/2018 *
 */

public class UnknownAircraftTypeException extends AvajLauncherException {
    public UnknownAircraftTypeException() {
        super();
    }

    public UnknownAircraftTypeException(String message) {
        super(message);
    }
}