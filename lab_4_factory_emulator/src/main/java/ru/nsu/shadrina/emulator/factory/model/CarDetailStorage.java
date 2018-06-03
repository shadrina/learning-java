package ru.nsu.shadrina.emulator.factory.model;

import ru.nsu.shadrina.emulator.factory.model.details.CarDetail;

import java.util.ArrayList;
import java.util.List;

public class CarDetailStorage<T extends CarDetail> {
    private boolean isEmpty = true;
    private boolean isFull = false;
    private int capacity;
    private List<T> details = new ArrayList<T>();

    public CarDetailStorage(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addDetail(T detail) {
        try {
            while (isFull) {
                System.out.println("Storage is full, need to wait");
                wait();
            }
            if (details.size() == capacity - 1) {
                isFull = true;
                notifyAll();
            }
            if (details.size() == 0) {
                isEmpty = false;
                notifyAll();
            }
            details.add(detail);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized T getDetail() {
        T detail = null;
        try {
            while (isEmpty) wait();
            if (details.size() == 1) {
                isEmpty = true;
                notifyAll();
            }
            if (details.size() == capacity) {
                isFull = false;
                notifyAll();
            }
            detail = details.get(0);
            details.remove(detail);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return detail;
    }

    public synchronized int getDetailsCount() {
        return details.size();
    }
}
