package ru.nsu.shadrina.emulator.factory.model;

import ru.nsu.shadrina.emulator.factory.model.details.Car;
import ru.nsu.shadrina.emulator.factory.model.details.CarAccessories;
import ru.nsu.shadrina.emulator.factory.model.details.CarBody;
import ru.nsu.shadrina.emulator.factory.model.details.Engine;
import ru.nsu.shadrina.emulator.factory.model.storage.CarDetailStorage;
import ru.nsu.shadrina.emulator.factory.model.storage.CarStorage;

import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {
    private static final int DEFAULT_SPEED = 2;
    private static final int DETAIL_ATTACHING_TIME = 1;

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

    Worker(CarDetailStorage<Engine> engineStorage, CarDetailStorage<CarBody> carBodyStorage, CarDetailStorage<CarAccessories> carAccessoriesStorage) {
        this.engineStorage = engineStorage;
        this.carBodyStorage = carBodyStorage;
        this.carAccessoriesStorage = carAccessoriesStorage;
    }

    public Car getCar() {
        return car;
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

    private void attachDetail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(DETAIL_ATTACHING_TIME);
    }

    private void relax() throws InterruptedException {
        TimeUnit.SECONDS.sleep(speed);
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
//                if (!hasOrder && car.isReady()) {
//                    System.out.println("Nobody needs me");
//                    break;
//                }
                if (!car.hasEngine()) {
                    car.setEngine(engineStorage.getDetail());
                    attachDetail();
                    System.out.println("Added engine");
                }
                if (!car.hasCarBody()) {
                    car.setCarBody(carBodyStorage.getDetail());
                    attachDetail();
                    System.out.println("Added carBody");
                }
                if (!car.hasCarAccessories()) {
                    car.setCarAccessories(carAccessoriesStorage.getDetail());
                    attachDetail();
                    System.out.println("Added carAccessories");
                }
                relax();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
