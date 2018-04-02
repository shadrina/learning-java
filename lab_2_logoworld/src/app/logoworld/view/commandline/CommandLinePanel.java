package app.logoworld.view.commandline;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommandLinePanel extends JPanel {
    private static final int PANEL_HEIGHT = 35;
    private static final int TEXTAREA_COLUMNS_COUNT = 10;
    private static final int EXECUTE_BUTTON_WIDTH = 85;
    private static final int EXECUTE_BUTTON_HEIGHT = 20;

    private EventListenerList listenerList = new EventListenerList();

    public CommandLinePanel() {
        Dimension size = getPreferredSize();
        size.height = PANEL_HEIGHT;
        setPreferredSize(size);

        JLabel commandLabel = new JLabel("Command: ");
        final JTextField commandField = new JTextField(TEXTAREA_COLUMNS_COUNT);
        JButton executeButton = new JButton("Execute");

        Dimension buttonSize = executeButton.getPreferredSize();
        buttonSize.width = EXECUTE_BUTTON_WIDTH;
        buttonSize.height = EXECUTE_BUTTON_HEIGHT;
        executeButton.setPreferredSize(buttonSize);

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireCommandLineEvent(new CommandLineEvent(this, commandField.getText().toUpperCase()));
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // First column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        add(commandLabel, gc);

        // Second column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        add(commandField, gc);

        // Third column
        gc.gridx = 2;
        gc.gridy = 0;
        add(executeButton, gc);
    }

    private void fireCommandLineEvent(CommandLineEvent e) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i +=2) {
            if (listeners[i] == CommandLineListener.class) {
                ((CommandLineListener)listeners[i + 1]).commandLineEventOccurred(e);
            }
        }
    }

    public void addCommandLineListener(CommandLineListener listener) {
        listenerList.add(CommandLineListener.class, listener);
    }

    public void removeCommandLineListener(CommandLineListener listener) {
        listenerList.remove(CommandLineListener.class, listener);
    }
}
