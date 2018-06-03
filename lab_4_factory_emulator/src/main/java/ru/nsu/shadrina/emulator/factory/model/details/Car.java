package ru.nsu.shadrina.emulator.factory.model.details;

public class Car {
    private Engine engine = null;
    private CarBody carBody = null;
    private CarAccessories carAccessories = null;

    public boolean hasEngine() {
        return engine != null;
    }

    public boolean hasCarBody() {
        return carBody != null;
    }

    public boolean hasCarAccessories() {
        return carAccessories != null;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public void setCarAccessories(CarAccessories carAccessories) {
        this.carAccessories = carAccessories;
    }

    public boolean isReady() {
        return engine != null && carBody != null && carAccessories != null;
    }
}
