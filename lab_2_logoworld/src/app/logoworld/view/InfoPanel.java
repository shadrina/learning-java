package app.logoworld.view;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private static final int PANEL_WIDTH = 135;
    private static final int HISTORY_PANEL_WIDTH = 120;
    private static final int HISTORY_PANEL_HEIGHT = 253;

    private TurtleInfo turtleInfo;
    private JTextArea history;

    public InfoPanel() {
        Dimension size = getPreferredSize();
        size.width = PANEL_WIDTH;
        setPreferredSize(size);

        turtleInfo = new TurtleInfo();
        JSeparator separator = new JSeparator();
        Dimension separatorSize = separator.getPreferredSize();
        separatorSize.height = 5;
        separator.setPreferredSize(separatorSize);
        history = new JTextArea();
        history.setPreferredSize(new Dimension(HISTORY_PANEL_WIDTH, HISTORY_PANEL_HEIGHT));

        setBorder(BorderFactory.createTitledBorder("Turtle Info"));
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        add(turtleInfo, gc);
        gc.gridy = 1;
        add(separator, gc);
        gc.gridy = 2;
        add(history, gc);
    }

    public void rememberAction(String action) {
        history.append(action + "\n");
    }
}
