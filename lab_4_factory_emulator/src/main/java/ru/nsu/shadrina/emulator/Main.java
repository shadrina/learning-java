package ru.nsu.shadrina.emulator;

import ru.nsu.shadrina.emulator.factory.controller.Courier;
import ru.nsu.shadrina.emulator.factory.controller.PlantManager;
import ru.nsu.shadrina.emulator.factory.controller.SalesManager;
import ru.nsu.shadrina.emulator.factory.model.FactoryModel;
import ru.nsu.shadrina.emulator.factory.view.FactoryView;
import ru.nsu.shadrina.emulator.threadpool.MyThreadPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlantManager plantManager = new PlantManager();
        FactoryModel factoryModel = plantManager.getModel();

        List<Runnable> runnablz = new ArrayList<>();
        runnablz.add(new FactoryView(plantManager));
        runnablz.add(new SalesManager(plantManager));
        runnablz.add(new Courier(factoryModel.getCustomers(), factoryModel.getCarStorage()));
        runnablz.addAll(Arrays.asList(factoryModel.getWorkers()));
        runnablz.addAll(factoryModel.getSuppliers());

        MyThreadPool.createThreadPool(runnablz);
    }
}
