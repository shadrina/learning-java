package ru.nsu.shadrina.emulator.factory.view;

import ru.nsu.shadrina.emulator.factory.controller.PlantManager;
import ru.nsu.shadrina.emulator.factory.view.factorycycle.FactoryCyclePanel;
import ru.nsu.shadrina.emulator.factory.view.userinfo.UserPanel;

import javax.swing.*;
import java.awt.*;

public class FactoryView implements Runnable {
    private PlantManager plantManager;

    public FactoryView(PlantManager plantManager) {
        this.plantManager = plantManager;
    }

    public void run() {
        JFrame f = new JFrame("Factory Emulator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridBagLayout());

        Container c = f.getContentPane();
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        c.add(new FactoryCyclePanel(plantManager.getModel()), gc);
        gc.gridy = 1;
        // c.add(new UserPanel(), gc);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
