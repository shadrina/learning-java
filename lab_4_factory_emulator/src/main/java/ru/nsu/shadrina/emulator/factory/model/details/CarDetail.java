package ru.nsu.shadrina.emulator.factory.model.details;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class CarDetail {
    private static final String RSC_PATH = "/images/";

    private String name;

    CarDetail() {
        this.name = this.getClass().getSimpleName().toLowerCase();
    }

    public String getName() {
        return name;
    }
}
