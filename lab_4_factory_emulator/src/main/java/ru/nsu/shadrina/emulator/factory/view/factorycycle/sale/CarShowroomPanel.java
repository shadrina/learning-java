package ru.nsu.shadrina.emulator.factory.view.factorycycle.sale;

import ru.nsu.shadrina.emulator.factory.model.storage.CarStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarShowroomPanel extends JPanel implements ActionListener {
    private static final int DELAY = 50;
    private static final Font DEFAULT_FONT = new Font("Tahoma", Font.PLAIN, 20);
    private static final int BORDER_THICKNESS = 2;

    private Timer timer = new Timer(DELAY, this);
    private CarStorage carStorage;
    private int capacity;

    public CarShowroomPanel(CarStorage carStorage) {
        this.carStorage = carStorage;
        this.capacity = carStorage.getCapacity();

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, BORDER_THICKNESS), "Showroom"));
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        g.setFont(DEFAULT_FONT);
        FontMetrics metrics = g.getFontMetrics(DEFAULT_FONT);
        String text = carStorage.getCarsCount() + " / " + capacity;
        int textWidth = metrics.stringWidth(text);
        g.drawString(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + metrics.getHeight() / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }
}
