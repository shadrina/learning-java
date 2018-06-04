package ru.nsu.shadrina.emulator.factory.view.factorycycle;

import ru.nsu.shadrina.emulator.factory.model.FactoryModel;
import ru.nsu.shadrina.emulator.factory.view.factorycycle.production.WorkersPanel;
import ru.nsu.shadrina.emulator.factory.view.factorycycle.production.StoragePanel;
import ru.nsu.shadrina.emulator.factory.view.factorycycle.sale.CarShowroomPanel;
import ru.nsu.shadrina.emulator.factory.view.factorycycle.sale.CustomersPanel;

import javax.swing.*;
import java.awt.*;

public class FactoryCyclePanel extends JPanel {
    private static final int FACTORY_CYCLE_PANEL_WIDTH = 500;
    private static final int FACTORY_CYCLE_PANEL_HEIGHT = 200;
    private static final int LEFT_PANEL_WIDTH = 100;
    private static final int LEFT_PANEL_HEIGHT = 170;
    private static final int LEFT_PANEL_LAYOUT_ROWS = 3;
    private static final int LEFT_PANEL_LAYOUT_COLUMNS = 1;
    private static final int RIGHT_PANEL_WIDTH = 120;
    private static final int RIGHT_PANEL_HEIGHT = 170;
    private static final int RIGHT_PANEL_LAYOUT_ROWS = 2;
    private static final int RIGHT_PANEL_LAYOUT_COLUMNS = 1;

    public FactoryCyclePanel(FactoryModel factoryModel) {
        setPreferredSize(new Dimension(FACTORY_CYCLE_PANEL_WIDTH, FACTORY_CYCLE_PANEL_HEIGHT));
        setLayout(new GridBagLayout());

        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT));
        leftSidePanel.setLayout(new GridLayout(LEFT_PANEL_LAYOUT_ROWS, LEFT_PANEL_LAYOUT_COLUMNS));
        leftSidePanel.add(new StoragePanel("Engines", factoryModel.getEngineStorage()));
        leftSidePanel.add(new StoragePanel("Bodies", factoryModel.getCarBodyStorage()));
        leftSidePanel.add(new StoragePanel("Accessories", factoryModel.getCarAccessoriesStorage()));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        add(leftSidePanel);
        gc.gridx = 1;
        add(new WorkersPanel(factoryModel.getWorkers()));
        gc.gridx = 2;

        JPanel rightSidePanel = new JPanel();
        rightSidePanel.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, RIGHT_PANEL_HEIGHT));
        rightSidePanel.setLayout(new GridLayout(RIGHT_PANEL_LAYOUT_ROWS, RIGHT_PANEL_LAYOUT_COLUMNS));
        rightSidePanel.add(new CarShowroomPanel(factoryModel.getCarStorage()));
        rightSidePanel.add(new CustomersPanel(factoryModel.getCustomers()));

        add(rightSidePanel, gc);
    }
}
