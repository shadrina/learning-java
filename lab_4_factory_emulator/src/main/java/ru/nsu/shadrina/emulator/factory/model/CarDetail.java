package ru.nsu.shadrina.emulator.factory.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CarDetail {
    private static final String RSC_PATH = "/images/";

    private String name;
    private BufferedImage img;

    public CarDetail() {
        this.name = this.getClass().getSimpleName().toLowerCase();
        /*try {
            InputStream is = CarDetail.class.getResourceAsStream(RSC_PATH + this.name + ".png");
            img = ImageIO.read(is);
        } catch(IOException ex) {
            ex.printStackTrace();
        }*/
    }

    public String getName() {
        return name;
    }
}
