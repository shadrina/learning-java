package app.logoworld.view;

import javax.swing.*;
import java.awt.*;

public class LogoWorldView implements ViewCommons {

    // Source:
    // https://stackoverflow.com/questions/7434845/setting-the-default-font-of-swing-program
    private static void setUIFont(javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public static void createGUI() {
        setUIFont(new javax.swing.plaf.FontUIResource(APP_FONT_NAME,Font.PLAIN,12));
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new AppFrame("LogoWorld");
            frame.setBounds(APP_FRAME_X, APP_FRAME_Y, APP_FRAME_WIDTH, APP_FRAME_HEIGHT);
            frame.setSize(APP_FRAME_WIDTH, APP_FRAME_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
