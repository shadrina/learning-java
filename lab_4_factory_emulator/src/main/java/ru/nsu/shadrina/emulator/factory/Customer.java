package ru.nsu.shadrina.emulator.factory;

import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {
    private Storage storage;

    public Customer(Storage storage) {
        this.storage = storage;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Customer need detail!");
                storage.getDetail();
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
