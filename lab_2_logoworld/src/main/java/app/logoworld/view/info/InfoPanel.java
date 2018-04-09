package app.logoworld.view.info;


import app.logoworld.view.field.state.TurtleState;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

public class InfoPanel extends JPanel implements InfoCommons {

    private TurtleInfo turtleInfo;
    private JEditorPane history;

    public InfoPanel() {
        Dimension size = getPreferredSize();
        size.width = INFO_PANEL_WIDTH;
        setPreferredSize(size);

        turtleInfo = new TurtleInfo();
        JSeparator separator = new JSeparator();
        Dimension separatorSize = separator.getPreferredSize();
        separatorSize.height = 5;
        separator.setPreferredSize(separatorSize);

        history = new JEditorPane();
        history.setEditable(false);
        history.setFont(new Font("Consolas", Font.BOLD, 13));
        JScrollPane scrollPane = new JScrollPane(history);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(HISTORY_PANEL_WIDTH, HISTORY_PANEL_HEIGHT));

        setBorder(BorderFactory.createTitledBorder("Turtle Info"));
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        add(turtleInfo, gc);
        gc.gridy = 1;
        add(separator, gc);
        gc.gridy = 2;
        add(scrollPane, gc);
    }

    public void rememberAction(String action) {
        append(action + "\n");
    }

    public void append(String s) {
        try {
            Document doc = history.getDocument();
            doc.insertString(doc.getLength(), s, null);
        } catch(BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public void refreshTurtleInfo(TurtleState ts) {
        turtleInfo.refresh(ts);
    }

}
