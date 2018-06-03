package ru.nsu.shadrina.emulator.factory.model.suppliers;

import ru.nsu.shadrina.emulator.factory.model.CarDetailStorage;
import ru.nsu.shadrina.emulator.factory.model.details.CarBody;

import java.util.concurrent.TimeUnit;

public class CarBodySupplier extends Supplier {
    private CarDetailStorage<CarBody> storage;

    public CarBodySupplier(CarDetailStorage<CarBody> storage) {
        super();
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                storage.addDetail(new CarBody());
                System.out.println("CarBodySupplier created new car body");
                TimeUnit.SECONDS.sleep(speed);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
