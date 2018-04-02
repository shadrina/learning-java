package app.logoworld.view.info;

import app.logoworld.view.field.state.TurtleState;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private static final int PANEL_WIDTH = 135;
    private static final int HISTORY_PANEL_WIDTH = 120;
    private static final int HISTORY_PANEL_HEIGHT = 300;
    private static final int MAX_HISTORY_ENTRIES = 19;

    private TurtleInfo turtleInfo;
    private JTextArea history;
    private int historyEntries = 0;

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
        history.setEditable(false);
        history.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        history.setPreferredSize(new Dimension(HISTORY_PANEL_WIDTH, HISTORY_PANEL_HEIGHT));
        history.append("\n");

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
        if (historyEntries == MAX_HISTORY_ENTRIES) {
            history.setText(null);
            history.append("\n");
            historyEntries = 0;
        }
        historyEntries++;
        history.setFont(new Font("Courier New", Font.BOLD, 12));
        history.append("  " + action + "\n");
    }

    public void refreshTurtleInfo(TurtleState ts) {
        turtleInfo.refresh(ts);
    }

}
