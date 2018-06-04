package ru.nsu.shadrina.emulator.factory.controller;

import ru.nsu.shadrina.emulator.factory.model.Customer;
import ru.nsu.shadrina.emulator.factory.model.storage.CarStorage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Courier implements Runnable {
    private static final int RELAX_TIME = 2;
    private List<Customer> customers;
    private CarStorage carStorage;

    public Courier(List<Customer> customers, CarStorage carStorage) {
        this.customers = customers;
        this.carStorage = carStorage;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (customers.size() == 0) {
                    TimeUnit.SECONDS.sleep(RELAX_TIME);
                    continue;
                }
                customers.get(0).receiveCar(carStorage.getCar());
                customers.remove(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
