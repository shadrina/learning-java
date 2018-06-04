package ru.nsu.shadrina.emulator.factory.controller;

import ru.nsu.shadrina.emulator.factory.model.Customer;
import ru.nsu.shadrina.emulator.factory.model.storage.CarStorage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SalesManager implements Runnable {
    private static final int CUSTOMER_SEARCH_TIME = 1800;

    private PlantManager plantManager;

    public SalesManager(PlantManager plantManager) {
        this.plantManager = plantManager;
    }

    @Override
    public void run() {
        try {
            List<Customer> customers = plantManager.getModel().getCustomers();

            while (true) {
                customers.add(new Customer());
                plantManager.placeOrder();
                TimeUnit.MILLISECONDS.sleep(CUSTOMER_SEARCH_TIME);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
