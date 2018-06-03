package ru.nsu.shadrina.emulator;

import ru.nsu.shadrina.emulator.factory.controller.CompanyManager;
import ru.nsu.shadrina.emulator.factory.model.FactoryModel;
import ru.nsu.shadrina.emulator.factory.model.Worker;
import ru.nsu.shadrina.emulator.factory.model.CarDetailStorage;
import ru.nsu.shadrina.emulator.factory.model.suppliers.Supplier;
import ru.nsu.shadrina.emulator.factory.view.FactoryView;
import ru.nsu.shadrina.emulator.threadpool.MyThreadPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CompanyManager companyManager = new CompanyManager();
        FactoryModel factoryModel = companyManager.getModel();

        List<Runnable> runnablz = new ArrayList<>();
        runnablz.add(new Customer(companyManager));
        runnablz.addAll(Arrays.asList(factoryModel.getEngineSuppliers()));
        runnablz.addAll(Arrays.asList(factoryModel.getCarBodySuppliers()));
        runnablz.addAll(Arrays.asList(factoryModel.getCarAccessoriesSuppliers()));
        runnablz.addAll(Arrays.asList(factoryModel.getWorkers()));

        MyThreadPool.createThreadPool(runnablz);
    }
}
