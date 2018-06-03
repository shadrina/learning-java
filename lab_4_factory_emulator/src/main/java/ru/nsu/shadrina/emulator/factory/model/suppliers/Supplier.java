package ru.nsu.shadrina.emulator.factory.model.suppliers;

public abstract class Supplier implements Runnable {
    private static final int DEFAULT_SPEED = 5;

    protected int speed = DEFAULT_SPEED;

    public abstract void run();

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
