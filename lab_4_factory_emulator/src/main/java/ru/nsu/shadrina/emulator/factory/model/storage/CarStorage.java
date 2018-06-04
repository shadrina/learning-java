package ru.nsu.shadrina.emulator.factory.model.storage;

import ru.nsu.shadrina.emulator.factory.model.details.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CarStorage {
    private static final int CAR_PREPARE_TIME = 3;

    private boolean isEmpty = true;
    private boolean isFull = false;
    private int capacity;
    private List<Car> cars = new ArrayList<>();

    public CarStorage(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCarsCount() {
        return cars.size();
    }

    public synchronized boolean isEmpty() {
        return isEmpty;
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

    private void prepareForSale() throws InterruptedException {
        TimeUnit.SECONDS.sleep(CAR_PREPARE_TIME);
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
            prepareForSale();
            cars.remove(car);
            System.out.println("Gave 1 car to customer");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return car;
    }
}
