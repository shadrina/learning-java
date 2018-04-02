package app.logoworld.view;

import app.logoworld.view.commandline.CommandLineEvent;
import app.logoworld.view.commandline.CommandLineListener;
import app.logoworld.view.commandline.CommandLinePanel;
import app.logoworld.view.field.FieldEvent;
import app.logoworld.view.field.FieldListener;
import app.logoworld.view.field.FieldPanel;
import app.logoworld.view.info.InfoPanel;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private FieldPanel field;
    private InfoPanel info;
    private CommandLinePanel commandLine;

    public AppFrame(String title) {
        super(title);

        setLayout(new BorderLayout());

        field = new FieldPanel();
        info = new InfoPanel();
        commandLine = new CommandLinePanel();

        commandLine.addCommandLineListener(new CommandLineListener() {
            public void commandLineEventOccurred(CommandLineEvent e) {
                field.reactOnCommand(e.getCommand(), e.getArgs());
                info.rememberAction(e.getMessage());
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
