package ru.nsu.shadrina.emulator;

import ru.nsu.shadrina.emulator.factory.controller.CompanyManager;

public class Customer implements Runnable {
    private CompanyManager companyManager;

    Customer(CompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    @Override
    public void run() {
        companyManager.placeOrder();
        companyManager.placeOrder();
        companyManager.placeOrder();
        companyManager.placeOrder();
        companyManager.placeOrder();
    }
}
