package ru.nsu.shadrina.emulator.factory.model.suppliers;

import ru.nsu.shadrina.emulator.factory.model.storage.CarDetailStorage;
import ru.nsu.shadrina.emulator.factory.model.details.CarAccessories;

import java.util.concurrent.TimeUnit;

public class CarAccessoriesSupplier extends Supplier {
    private CarDetailStorage<CarAccessories> storage;

    public CarAccessoriesSupplier(CarDetailStorage<CarAccessories> storage) {
        super();
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 130; i++) {
                storage.addDetail(new CarAccessories());
                System.out.println("CarAccessoriesSupplier created new accessories");
                TimeUnit.SECONDS.sleep(speed);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
