package ru.nsu.shadrina.emulator.factory.model;

import ru.nsu.shadrina.emulator.factory.model.details.Car;

public class Customer {
    public enum Mood { WAITING, HAPPY, ANGRY }

    private Mood mood = Mood.WAITING;
    private Car newCar = null;

    public void receiveCar(Car car) {
        if (car == null) this.mood = Mood.ANGRY;
        else {
            this.newCar = car;
            this.mood = Mood.HAPPY;
        }
    }

    public Mood getMood() {
        return this.mood;
    }

    public boolean hasCar() {
        return newCar != null;
    }
}
