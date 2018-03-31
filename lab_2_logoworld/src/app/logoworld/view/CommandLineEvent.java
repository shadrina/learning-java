package app.logoworld.view;

import java.util.EventObject;

public class CommandLineEvent extends EventObject {
    private String command;

    public CommandLineEvent(Object source, String command) {
        super(source);
        this.command = command;
    }

    public String getMessage() {
        return "You executed " + command;
    }
}
