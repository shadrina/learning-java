package ru.nsu.shadrina.emulator.factory.view.factorycycle;

import javax.swing.*;
import java.awt.*;

public class FactoryCyclePanel extends JPanel {


    {
        setBorder(BorderFactory.createTitledBorder("FactoryCycle"));
        setPreferredSize(new Dimension(700, 400));
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
    }
}
