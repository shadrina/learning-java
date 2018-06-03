package ru.nsu.shadrina.emulator.factory;

import ru.nsu.shadrina.emulator.factory.model.CarDetail;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private boolean isEmpty = true;
    private boolean isFull = false;
    private int capacity;
    private List<CarDetail> details = new ArrayList<CarDetail>();

    public Storage(int capacity) {
        this.capacity = capacity;
    }

    synchronized public void addDetail(CarDetail detail) {
        try {
            while (isFull) wait();
            if (details.size() == capacity - 1) {
                isFull = true;
                notifyAll();
            }
            if (details.size() == 0) {
                isEmpty = false;
                notifyAll();
            }
            System.out.println("New detail is in the storage");
            details.add(detail);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public CarDetail getDetail() {
        CarDetail detail = null;
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
            System.out.println("Gave 1 detail to the customer");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return detail;
    }
}
