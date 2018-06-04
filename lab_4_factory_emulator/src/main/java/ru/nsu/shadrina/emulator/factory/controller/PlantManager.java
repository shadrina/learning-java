package ru.nsu.shadrina.emulator.factory.controller;

import ru.nsu.shadrina.emulator.factory.model.Customer;
import ru.nsu.shadrina.emulator.factory.model.FactoryModel;
import ru.nsu.shadrina.emulator.factory.model.Worker;
import ru.nsu.shadrina.emulator.factory.model.details.Car;

public class PlantManager {
    private FactoryModel model = new FactoryModel();

    public PlantManager() {
        placeOrder();
        placeOrder();
        placeOrder();
        placeOrder();
    }

    public void placeOrder() {
        Worker[] workers = model.getWorkers();
        int i = 0;
        while (workers[i].isBusy()) {
            i++;
            if (i == workers.length) i = 0;
        }
        workers[i].makeOrder();
    }

    public FactoryModel getModel() {
        return model;
    }
}
