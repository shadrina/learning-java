package ru.nsu.shadrina.emulator.factory;

import ru.nsu.shadrina.emulator.factory.model.CarDetail;

import java.util.concurrent.TimeUnit;

public class Supplier implements Runnable {
    private Storage storage;

    public Supplier(Storage storage) {
        this.storage = storage;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                storage.addDetail(new CarDetail());
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
