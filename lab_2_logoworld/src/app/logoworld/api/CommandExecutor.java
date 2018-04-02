package app.logoworld.api;

import app.logoworld.view.field.FieldPanel;
import org.jetbrains.annotations.NotNull;

public class CommandExecutor {

    public static void executeCommand(@NotNull String commandName, String[] args, FieldPanel context) {
        Command command = CommandFactory.create(commandName.toUpperCase());
        command.execute(args, context);
    }
}
