package ru.nsu.shadrina.emulator;

import ru.nsu.shadrina.emulator.factory.Storage;
import ru.nsu.shadrina.emulator.factory.Customer;
import ru.nsu.shadrina.emulator.factory.Supplier;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(5);
        Thread customer = new Thread(new Customer(storage));
        Thread supplier = new Thread(new Supplier(storage));

        supplier.start();
        customer.start();

        try {
            customer.join();
            supplier.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
