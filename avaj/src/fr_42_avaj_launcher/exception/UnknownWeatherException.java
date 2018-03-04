package fr_42_avaj_launcher.exception;

/****************************************
 * Created by snadaras                  *
 * snadaras@student.42.fr on 20/02/2018 *
 */

public class UnknownWeatherException extends AvajLauncherException {
    public UnknownWeatherException() {
        super();
    }

    public UnknownWeatherException(String message) {
        super(message);
    }
}