package ru.nsu.shadrina.emulator.factory.view.factorycycle.production;

import ru.nsu.shadrina.emulator.factory.model.Worker;
import ru.nsu.shadrina.emulator.factory.model.details.Car;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class WorkerStatePanel extends JPanel implements ActionListener {
    private static final int DELAY = 50;
    private static final String RSC_PATH = "/images/";
    private static final int IMAGE_OFFSET = 15;

    private Timer timer = new Timer(DELAY, this);
    private static Image plainCarImg;
    private static Image carWithCarBodyImg;
    private static Image carWithAccessoriesImg;
    private static Image completeCarImg;
    private static Image fullCompleteCarImg;

    private Worker worker;
    private Image currentStateImg = plainCarImg;

    static {
        try {
            InputStream is = WorkerStatePanel.class.getResourceAsStream(RSC_PATH + "plain-car.png");
            plainCarImg = ImageIO.read(is);
            is = WorkerStatePanel.class.getResourceAsStream(RSC_PATH + "car-with-car-body.png");
            carWithCarBodyImg = ImageIO.read(is);
            is = WorkerStatePanel.class.getResourceAsStream(RSC_PATH + "car-with-accessories.png");
            carWithAccessoriesImg = ImageIO.read(is);
            is = WorkerStatePanel.class.getResourceAsStream(RSC_PATH + "complete-car.png");
            completeCarImg = ImageIO.read(is);
            is = WorkerStatePanel.class.getResourceAsStream(RSC_PATH + "full-complete-car.png");
            fullCompleteCarImg = ImageIO.read(is);

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    WorkerStatePanel(Worker worker) {
        this.worker = worker;
        timer.start();
    }

    private void checkState() {
        Car car = worker.getCar();
        if (car.isReady()) {
            currentStateImg = fullCompleteCarImg;
        } else if (car.hasCarBody() && car.hasCarAccessories()) {
            currentStateImg = completeCarImg;
        } else if (car.hasCarBody() && !car.hasCarAccessories()) {
            currentStateImg = carWithCarBodyImg;
        } else if (car.hasCarAccessories() && !car.hasCarBody()) {
            currentStateImg = carWithAccessoriesImg;
        } else {
            currentStateImg = plainCarImg;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        checkState();

        double widthChangeRatio = getWidth() * 1.0 / currentStateImg.getWidth(this);
        double heightChangeRatio = getHeight() * 1.0 / currentStateImg.getHeight(this);
        double ratio = heightChangeRatio < widthChangeRatio ? heightChangeRatio : widthChangeRatio;
        int newWidth = (int)(currentStateImg.getWidth(this) * ratio) - IMAGE_OFFSET;
        int newHeight = (int)(currentStateImg.getHeight(this) * ratio) - IMAGE_OFFSET;
        g.drawImage(currentStateImg.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT),
                getWidth() / 2 - newWidth / 2,
                getHeight() / 2 - newHeight / 2,
                this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }
}
