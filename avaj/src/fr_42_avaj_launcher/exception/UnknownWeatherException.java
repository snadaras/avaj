package fr_42_avaj_launcher.exception;

/**
 * Created by snadaras on 20/02/2018.
 */
public class UnknownWeatherException extends AvajLauncherException {
    public UnknownWeatherException() {
        super();
    }

    public UnknownWeatherException(String message) {
        super(message);
    }
}