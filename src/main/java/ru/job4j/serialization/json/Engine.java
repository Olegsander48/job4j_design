package ru.job4j.serialization.json;

public class Engine {
    private final int horsePower;

    public Engine(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "horsePower=" + horsePower
                + '}';
    }
}
