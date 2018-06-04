package ru.nsu.shadrina.emulator.factory.view.factorycycle.sale;

import ru.nsu.shadrina.emulator.factory.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersPanel extends JPanel implements ActionListener {
    private static final int DELAY = 2500;
    private static final int CUSTOMERS_PANEL_WIDTH = 120;
    private static final int CUSTOMERS_PANEL_HEIGHT = 170;
    private static final int LAYOUT_ROWS = 1;
    private static final int LAYOUT_COLUMNS = 3;
    private static final int BORDER_THICKNESS = 2;

    private Timer timer = new Timer(DELAY, this);
    private List<Customer> customers;
    private Map<Customer, CustomerMoodPanel> panels = new HashMap<>();
    private List<CustomerMoodPanel> deletionQueue = new ArrayList<>();
    private boolean timeToRevalidate = true;

    public CustomersPanel(List<Customer> customers) {
        this.customers = customers;
        for (Customer customer: customers) {
            CustomerMoodPanel panel = new CustomerMoodPanel(customer);
            panels.put(customer, panel);
            add(panel);
        }

        setLayout(new GridLayout(LAYOUT_ROWS, LAYOUT_COLUMNS));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, BORDER_THICKNESS), "Customers"));
        setPreferredSize(new Dimension(CUSTOMERS_PANEL_WIDTH, CUSTOMERS_PANEL_HEIGHT));

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            deletionQueue.forEach(this::remove);
            deletionQueue.clear();
            customers.forEach(customer -> {
                if (!panels.containsKey(customer)) {
                    CustomerMoodPanel newPanel = new CustomerMoodPanel(customer);
                    panels.put(customer, newPanel);
                    add(newPanel);
                }
            });
            panels.keySet().forEach(customer -> {
                if (!customers.contains(customer)) {
                    deletionQueue.add(panels.get(customer));
                }
            });
            if (timeToRevalidate) revalidate();
            repaint();
            timeToRevalidate = !timeToRevalidate;
        }
    }
}
