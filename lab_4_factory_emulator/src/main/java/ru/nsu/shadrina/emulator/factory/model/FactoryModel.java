package ru.nsu.shadrina.emulator.factory.model;

import ru.nsu.shadrina.emulator.factory.model.details.CarAccessories;
import ru.nsu.shadrina.emulator.factory.model.details.CarBody;
import ru.nsu.shadrina.emulator.factory.model.details.Engine;
import ru.nsu.shadrina.emulator.factory.model.storage.CarDetailStorage;
import ru.nsu.shadrina.emulator.factory.model.storage.CarStorage;
import ru.nsu.shadrina.emulator.factory.model.suppliers.CarAccessoriesSupplier;
import ru.nsu.shadrina.emulator.factory.model.suppliers.CarBodySupplier;
import ru.nsu.shadrina.emulator.factory.model.suppliers.EngineSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FactoryModel {
    private static final String CFG_NAME = "/settings.cfg";
    private static Map<String, Integer> settings = new HashMap<>();
    static {
        InputStream inputstream = null;
        try {
            inputstream = FactoryModel.class.getResourceAsStream(CFG_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("=");
                settings.put(args[0], Integer.parseInt(args[1]));
            }
        }
        catch (IOException | NullPointerException e) {
            System.err.println(e.getLocalizedMessage());
        }
        finally {
            if (inputstream != null) {
                try { inputstream.close(); }
                catch (IOException e) { System.err.println(e.getLocalizedMessage()); }
            }
        }
    }

    private CarDetailStorage<Engine> engineStorage = new CarDetailStorage<>(settings.get("EngineStorageSize"));
    private CarDetailStorage<CarBody> carBodyStorage = new CarDetailStorage<>(settings.get("CarBodyStorageSize"));
    private CarDetailStorage<CarAccessories> carAccessoriesStorage = new CarDetailStorage<>(settings.get("CarAccessoriesStorageSize"));
    private CarStorage carStorage = new CarStorage(settings.get("CarStorageSize"));

    private EngineSupplier[] engineSuppliers = new EngineSupplier[settings.get("EngineSuppliersCount")];
    private CarBodySupplier[] carBodySuppliers = new CarBodySupplier[settings.get("CarBodySuppliersCount")];
    private CarAccessoriesSupplier[] carAccessoriesSuppliers = new CarAccessoriesSupplier[settings.get("CarAccessoriesSuppliersCount")];

    private Worker[] workers = new Worker[settings.get("WorkersCount")];

    private List<Customer> customers = new LinkedList<>();

    {
        for (int i = 0; i < engineSuppliers.length; i++) {
            engineSuppliers[i] = new EngineSupplier(engineStorage);
        }
        for (int i = 0; i < carBodySuppliers.length; i++) {
            carBodySuppliers[i] = new CarBodySupplier(carBodyStorage);
        }
        for (int i = 0; i < carAccessoriesSuppliers.length; i++) {
            carAccessoriesSuppliers[i] = new CarAccessoriesSupplier(carAccessoriesStorage);
        }

        Worker.setCarStorage(carStorage);
        final Random random = new Random();
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker(engineStorage, carBodyStorage, carAccessoriesStorage);
            workers[i].setSpeed(random.nextInt(10));
        }
    }

    public CarDetailStorage<Engine> getEngineStorage() {
        return engineStorage;
    }

    public CarDetailStorage<CarBody> getCarBodyStorage() {
        return carBodyStorage;
    }

    public CarDetailStorage<CarAccessories> getCarAccessoriesStorage() {
        return carAccessoriesStorage;
    }

    public CarStorage getCarStorage() {
        return carStorage;
    }

    public EngineSupplier[] getEngineSuppliers() {
        return engineSuppliers;
    }

    public CarBodySupplier[] getCarBodySuppliers() {
        return carBodySuppliers;
    }

    public CarAccessoriesSupplier[] getCarAccessoriesSuppliers() {
        return carAccessoriesSuppliers;
    }

    public List<Runnable> getSuppliers() {
        List<Runnable> suppliers = new ArrayList<>();
        suppliers.addAll(Arrays.asList(engineSuppliers));
        suppliers.addAll(Arrays.asList(carBodySuppliers));
        suppliers.addAll(Arrays.asList(carAccessoriesSuppliers));

        return  suppliers;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
