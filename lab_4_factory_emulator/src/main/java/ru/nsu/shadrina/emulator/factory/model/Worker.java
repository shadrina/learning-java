package ru.nsu.shadrina.emulator.factory.model;

import ru.nsu.shadrina.emulator.factory.model.details.Car;
import ru.nsu.shadrina.emulator.factory.model.details.CarAccessories;
import ru.nsu.shadrina.emulator.factory.model.details.CarBody;
import ru.nsu.shadrina.emulator.factory.model.details.Engine;

import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {
    private static final int DEFAULT_SPEED = 4;

    private static CarStorage carStorage;
    public static void setCarStorage(CarStorage carStorage) {
        Worker.carStorage = carStorage;
    }

    private int speed = DEFAULT_SPEED;
    private boolean hasOrder = false;
    private CarDetailStorage<Engine> engineStorage;
    private CarDetailStorage<CarBody> carBodyStorage;
    private CarDetailStorage<CarAccessories> carAccessoriesStorage;
    private Car car = new Car();

    public Worker(CarDetailStorage<Engine> engineStorage, CarDetailStorage<CarBody> carBodyStorage, CarDetailStorage<CarAccessories> carAccessoriesStorage) {
        this.engineStorage = engineStorage;
        this.carBodyStorage = carBodyStorage;
        this.carAccessoriesStorage = carAccessoriesStorage;
    }

    public synchronized boolean isBusy() {
        return this.hasOrder;
    }

    public synchronized void makeOrder() {
        this.hasOrder = true;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void run() {
        try {
            while (true) {
                if (hasOrder && car.isReady()) {
                    System.out.println("Completed car!");
                    carStorage.addCar(car);
                    hasOrder = false;
                    car = new Car();
                }
                if (!hasOrder && car.isReady()) {
                    System.out.println("Nobody needs me");
                    break;
                }
                if (!car.hasEngine()) {
                    car.setEngine(engineStorage.getDetail());
                    System.out.println("Added engine");
                }
                if (!car.hasCarBody()) {
                    car.setCarBody(carBodyStorage.getDetail());
                    System.out.println("Added carBody");
                }
                if (!car.hasCarAccessories()) {
                    car.setCarAccessories(carAccessoriesStorage.getDetail());
                    System.out.println("Added carAccessories");
                }
                TimeUnit.SECONDS.sleep(speed);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
