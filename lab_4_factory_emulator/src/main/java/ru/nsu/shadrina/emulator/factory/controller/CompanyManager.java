package ru.nsu.shadrina.emulator.factory.controller;

import ru.nsu.shadrina.emulator.factory.model.FactoryModel;
import ru.nsu.shadrina.emulator.factory.model.Worker;

public class CompanyManager {
    FactoryModel model = new FactoryModel();

    public void placeOrder() {
        Worker[] workers = model.getWorkers();
        int i = 0;
        while (workers[i].isBusy()) {
            i++;
            if (i == workers.length) i = 0;
        }
        workers[i].makeOrder();
    }

    public void deliverOrder() {

    }

    public FactoryModel getModel() {
        return model;
    }
}
