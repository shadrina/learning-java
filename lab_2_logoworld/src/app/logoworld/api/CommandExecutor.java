package app.logoworld.api;

import app.logoworld.exception.InvalidArgumentException;
import app.logoworld.exception.InvalidCommandException;
import app.logoworld.exception.LogoWorldException;
import app.logoworld.view.field.FieldPanel;
import org.jetbrains.annotations.NotNull;

public class CommandExecutor {

    public static void executeCommand(@NotNull String commandName, String[] args, FieldPanel context) throws LogoWorldException {
        Command command = CommandFactory.create(commandName.toUpperCase());
        command.execute(args, context);
    }
}
