package ru.nsu.shadrina.emulator.factory.view.factorycycle;

import javax.swing.*;
import java.awt.*;

public class FactoryWorkerPanel extends JPanel {
    private static Image plainCarImg;
    private static Image carWithCarBodyImg;
    private static Image carWithAccessoriesImg;
    private static Image completeCarImg;


    private Image currentStateImg = plainCarImg;

    static {

    }

    {
        setPreferredSize(new Dimension(40, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
