package app.logoworld.view;

import javax.swing.*;
import java.awt.*;

public class TurtleInfo extends JPanel {
    private static final int PANEL_WIDTH = 120;
    private static final int PANEL_HEIGHT = 140;

    public TurtleInfo() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createEtchedBorder());
    }
}
