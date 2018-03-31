package app.logoworld.view;

import javax.swing.*;

public class LogoWorldView {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public static void createGUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new AppFrame("LogoWorld");
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
