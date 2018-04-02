package app.logoworld.view.field.state;

import app.logoworld.view.field.FieldCommons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class CellPanel extends JPanel implements FieldCommons {

    private static Map<Character, BufferedImage> turtleImages = new TreeMap<>();
    private static JLabel turtleLabel = null;

    private boolean hasArtifact = false;
    private Color artifactColor = null;
    private boolean isColored = false;
    private Color color = null;

    static {
        BufferedImage turtleImageU = null;
        try {
            InputStream is = CellPanel.class.getResourceAsStream(RSC_PATH + "turtle-u.png");
            turtleImageU = ImageIO.read(is);

            is = CellPanel.class.getResourceAsStream(RSC_PATH + "turtle-d.png");
            BufferedImage turtleImageD = ImageIO.read(is);

            is = CellPanel.class.getResourceAsStream(RSC_PATH + "turtle-r.png");
            BufferedImage turtleImageR = ImageIO.read(is);

            is = CellPanel.class.getResourceAsStream(RSC_PATH + "turtle-l.png");
            BufferedImage turtleImageL = ImageIO.read(is);

            turtleImages.put('U', turtleImageU);
            turtleImages.put('D', turtleImageD);
            turtleImages.put('R', turtleImageR);
            turtleImages.put('L', turtleImageL);
        }  catch(IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (turtleImageU != null) {
                turtleLabel = new JLabel(new ImageIcon(turtleImageU));
            }
        }
    }

    public CellPanel() {
        setLayout(new BorderLayout());
    }

    public boolean hasArtifact() {
        return hasArtifact;
    }

    public Color getArtifactColor() {
        return artifactColor;
    }

    public void setArtifact(Color color) {
        this.hasArtifact = true;
        this.artifactColor = color;
    }

    public void setColor(Color color) {
        this.isColored = true;
        this.color = color;
    }

    public void renderTurtle(char direction) {
        turtleLabel = new JLabel(new ImageIcon(turtleImages.get(direction)));
        add(turtleLabel, BorderLayout.CENTER);
    }

    public void removeTurtle() {
        remove(turtleLabel);
    }

    public void removeArtifact() {
        this.hasArtifact = false;
        this.artifactColor = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isColored) {
            g.setColor(color);
            g.fillRect(0 , 0, getWidth(), getHeight());
        }
        if (hasArtifact) {
            g.setColor(artifactColor);
            g.fillOval(
                    getWidth() / 2 - ARTIFACT_DIAMETER / 2,
                    getHeight() / 2 - ARTIFACT_DIAMETER / 2,
                    ARTIFACT_DIAMETER,
                    ARTIFACT_DIAMETER
            );
        }
    }
}
