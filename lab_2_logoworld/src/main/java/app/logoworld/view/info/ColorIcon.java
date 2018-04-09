package app.logoworld.view.info;


import javax.swing.*;
import java.awt.*;

public class ColorIcon extends JPanel implements InfoCommons {

    private Color color = null;

    ColorIcon() {
        setPreferredSize(new Dimension(COLORICON_WIDTH, COLORICON_HEIGHT));
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (color != null) {
            g.setColor(color);
            g.fillRect(2, 2, getWidth(), getHeight());
        }
    }
}
