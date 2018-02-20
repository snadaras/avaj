package fr_42_avaj_launcher.craft_engine;

/**
 * Created by snadaras on 20/02/2018.
 */

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private long nextId() {
        return ++idCounter;
    }
}