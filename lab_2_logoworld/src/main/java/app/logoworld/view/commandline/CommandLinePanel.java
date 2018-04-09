package app.logoworld.view.commandline;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommandLinePanel extends JPanel implements CommandLineCommons {

    private EventListenerList listenerList = new EventListenerList();

    public CommandLinePanel() {
        Dimension size = getPreferredSize();
        size.height = PANEL_HEIGHT;
        setPreferredSize(size);

        JLabel commandLabel = new JLabel("Command: ");
        final JTextField commandField = new JTextField(TEXTAREA_COLUMNS_COUNT);
        JButton executeButton = new JButton("Execute");

        executeButton.setPreferredSize(new Dimension(EXECUTE_BUTTON_WIDTH, EXECUTE_BUTTON_HEIGHT));

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireCommandLineEvent(new CommandLineEvent(this, commandField.getText().toUpperCase()));
            }
        });
        executeButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    fireCommandLineEvent(new CommandLineEvent(this, "MOVE U 1"));
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    fireCommandLineEvent(new CommandLineEvent(this, "MOVE R 1"));
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    fireCommandLineEvent(new CommandLineEvent(this, "MOVE L 1"));
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    fireCommandLineEvent(new CommandLineEvent(this, "MOVE D 1"));
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        commandField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fireCommandLineEvent(new CommandLineEvent(this, commandField.getText().toUpperCase()));
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
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
        add(commandField, gc);

        // Third column
        gc.gridx = 2;
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
