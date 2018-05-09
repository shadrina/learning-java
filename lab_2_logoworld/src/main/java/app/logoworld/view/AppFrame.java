package app.logoworld.view;


import app.logoworld.exception.InvalidArgumentException;
import app.logoworld.exception.InvalidCommandException;
import app.logoworld.exception.NotInitializedException;
import app.logoworld.view.commandline.CommandLineEvent;
import app.logoworld.view.commandline.CommandLineListener;
import app.logoworld.view.commandline.CommandLinePanel;
import app.logoworld.view.field.FieldEvent;
import app.logoworld.view.field.FieldListener;
import app.logoworld.view.field.FieldPanel;
import app.logoworld.view.info.InfoPanel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

class AppFrame extends JFrame {

    private static final Logger log = Logger.getLogger(AppFrame.class);

    private FieldPanel field;
    private InfoPanel info;
    private CommandLinePanel commandLine;

    AppFrame(String title) {
        super(title);

        setLayout(new BorderLayout());

        field = new FieldPanel();
        info = new InfoPanel();
        commandLine = new CommandLinePanel();

        final AppFrame self = this;
        commandLine.addCommandLineListener(new CommandLineListener() {
            public void commandLineEventOccurred(CommandLineEvent e) {
                try {
                    field.reactOnCommand(e.getCommand(), e.getArgs());
                    info.rememberAction(e.getMessage());
                } catch (NotInitializedException ex) {
                    log.warn("User didn't initialize!", ex);
                    JOptionPane.showMessageDialog(self, ex.getMessage(), ex.getTitle(), JOptionPane.INFORMATION_MESSAGE);
                } catch (InvalidCommandException | InvalidArgumentException ex) {
                    log.warn("Invalid input!", ex);
                    JOptionPane.showMessageDialog(self, ex.getMessage(), ex.getTitle(), JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
                    log.error("Uncaught exception!", ex);
                    JOptionPane.showMessageDialog(self, ex.toString(), "Uncaught exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        field.addFieldListener(new FieldListener() {
            public void fieldEventOccurred(FieldEvent e) {
                info.refreshTurtleInfo(e.getTurtleState());
            }
        });

        Container c = getContentPane();

        c.add(field, BorderLayout.EAST);
        c.add(info, BorderLayout.WEST);
        c.add(commandLine, BorderLayout.SOUTH);
    }
}
