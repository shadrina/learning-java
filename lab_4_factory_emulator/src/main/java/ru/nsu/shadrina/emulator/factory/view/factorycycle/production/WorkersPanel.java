package ru.nsu.shadrina.emulator.factory.view.factorycycle.production;

import ru.nsu.shadrina.emulator.factory.model.Worker;

import javax.swing.*;
import java.awt.*;

public class WorkersPanel extends JPanel {
    private static final int FACTORY_PANEL_WIDTH = 270;
    private static final int FACTORY_PANEL_HEIGHT = 170;
    private static final int LAYOUT_ROWS = 2;
    private static final int LAYOUT_COLUMNS = 1;
    private static final int BORDER_THICKNESS = 2;

    public WorkersPanel(Worker[] workers) {
        for (Worker worker: workers) {
            add(new WorkerStatePanel(worker));
        }

        setLayout(new GridLayout(LAYOUT_ROWS, LAYOUT_COLUMNS));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, BORDER_THICKNESS), "Workers"));
        setPreferredSize(new Dimension(FACTORY_PANEL_WIDTH, FACTORY_PANEL_HEIGHT));
    }
}
