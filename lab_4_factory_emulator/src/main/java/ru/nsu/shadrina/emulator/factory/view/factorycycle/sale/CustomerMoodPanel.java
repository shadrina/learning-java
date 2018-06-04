package ru.nsu.shadrina.emulator.factory.view.factorycycle.sale;

import ru.nsu.shadrina.emulator.factory.model.Customer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class CustomerMoodPanel extends JPanel implements ActionListener {
    private static final int DELAY = 50;
    private static final String RSC_PATH = "/images/customer/";
    private static final int IMAGE_OFFSET = 20;

    private Timer timer = new Timer(DELAY, this);
    private static Image waitingMoodImg;
    private static Image happyMoodImg;
    private static Image angryMoodImg;

    private Customer customer;
    private Image currentMoodImg = waitingMoodImg;

    static {
        try {
            InputStream is = CustomerMoodPanel.class.getResourceAsStream(RSC_PATH + "waiting-mood.png");
            waitingMoodImg = ImageIO.read(is);
            is = CustomerMoodPanel.class.getResourceAsStream(RSC_PATH + "happy-mood.png");
            happyMoodImg = ImageIO.read(is);
            is = CustomerMoodPanel.class.getResourceAsStream(RSC_PATH + "angry-mood.png");
            angryMoodImg = ImageIO.read(is);

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    CustomerMoodPanel(Customer customer) {
        this.customer = customer;
        timer.start();
    }

    private void checkState() {
        switch (customer.getMood()) {
            case WAITING:
                currentMoodImg = waitingMoodImg;
                break;
            case HAPPY:
                currentMoodImg = happyMoodImg;
                break;
            case ANGRY:
                currentMoodImg = angryMoodImg;
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        checkState();
        double ratio = getHeight() * 1.0 / currentMoodImg.getHeight(this);
        int newSize = (int)(getWidth() * ratio) + IMAGE_OFFSET;
        g.drawImage(currentMoodImg.getScaledInstance(newSize, newSize, Image.SCALE_DEFAULT),
                getWidth() / 2 - newSize / 2,
                getHeight() / 2 - newSize / 2,
                this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }
}
