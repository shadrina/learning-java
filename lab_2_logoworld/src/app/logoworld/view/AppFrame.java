package app.logoworld.view;

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
                // field.executeCommand(e.getCommand());
                info.rememberAction(e.getMessage());
            }
        });

        Container c = getContentPane();

        c.add(field, BorderLayout.EAST);
        c.add(info, BorderLayout.WEST);
        c.add(commandLine, BorderLayout.SOUTH);
    }
}
