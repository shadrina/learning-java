package app.logoworld.view.field;


import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
    private static final int HELPPANEL_WIDTH = 20;
    private static final int HELPPANEL_HEIGHT = 20;
    private static final int INIT_X_TEXT_SHIFT = 5;
    private static final int INIT_Y_TEXT_SHIFT = 5;

    HelpPanel() {
        setPreferredSize(new Dimension(HELPPANEL_WIDTH, HELPPANEL_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("Avaliable commands: ", INIT_X_TEXT_SHIFT, INIT_Y_TEXT_SHIFT);
    }
}
