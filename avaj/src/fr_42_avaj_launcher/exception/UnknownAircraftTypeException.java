package fr_42_avaj_launcher.exception;

/**
 * Created by snadaras on 20/02/2018.
 */
public class UnknownAircraftTypeException extends AvajLauncherException {
    public UnknownAircraftTypeException() {
        super();
    }

    public UnknownAircraftTypeException(String message) {
        super(message);
    }
}