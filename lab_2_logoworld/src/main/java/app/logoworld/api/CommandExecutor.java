package app.logoworld.api;


import app.logoworld.exception.LogoWorldException;
import app.logoworld.view.field.FieldPanel;

public class CommandExecutor {

    public static void executeCommand(String commandName, String[] args, FieldPanel context) throws LogoWorldException {
        Command command = CommandFactory.create(commandName.toUpperCase());
        command.execute(args, context);
    }
}
