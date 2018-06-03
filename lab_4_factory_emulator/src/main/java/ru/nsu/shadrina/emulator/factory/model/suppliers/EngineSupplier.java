package ru.nsu.shadrina.emulator.factory.model.suppliers;

import ru.nsu.shadrina.emulator.factory.model.CarDetailStorage;
import ru.nsu.shadrina.emulator.factory.model.details.Engine;

import java.util.concurrent.TimeUnit;

public class EngineSupplier extends Supplier {
    private CarDetailStorage<Engine> storage;

    public EngineSupplier(CarDetailStorage<Engine> storage) {
        super();
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                storage.addDetail(new Engine());
                System.out.println("EngineSupplier created new engine");
                TimeUnit.SECONDS.sleep(speed);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
