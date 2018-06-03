package ru.nsu.shadrina.emulator.factory.model;

import ru.nsu.shadrina.emulator.factory.model.details.Car;

import java.util.ArrayList;
import java.util.List;

public class CarStorage {
    private boolean isEmpty = true;
    private boolean isFull = false;
    private int capacity;
    private List<Car> cars = new ArrayList<Car>();

    CarStorage(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addCar(Car car) {
        try {
            while (isFull) wait();
            if (cars.size() == capacity - 1) {
                isFull = true;
                notifyAll();
            }
            if (cars.size() == 0) {
                isEmpty = false;
                notifyAll();
            }
            System.out.println("New car is in the storage");
            cars.add(car);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car getCar() {
        Car car = null;
        try {
            while (isEmpty) wait();
            if (cars.size() == 1) {
                isEmpty = true;
                notifyAll();
            }
            if (cars.size() == capacity) {
                isFull = false;
                notifyAll();
            }
            car = cars.get(0);
            cars.remove(car);
            System.out.println("Gave 1 car to customer");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return car;
    }
}
