package app.logoworld.api;


import app.logoworld.exception.InvalidArgumentException;
import app.logoworld.exception.InvalidCommandException;
import app.logoworld.exception.LogoWorldException;
import app.logoworld.exception.NotInitializedException;
import app.logoworld.view.field.FieldEvent;
import app.logoworld.view.field.FieldPanel;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

abstract class Command {
    public abstract void execute(String[] args, FieldPanel context) throws LogoWorldException;
    protected void checkIntArguments(@NotNull String commandName, String[] args) throws InvalidArgumentException {
        try {
            for (String arg : args) {
                if (Integer.parseInt(arg) < 0) {
                    throw new InvalidArgumentException(commandName);
                }
            }
        } catch (NumberFormatException ex) {
            throw new InvalidArgumentException(commandName);
        }
    }
}

class Init extends Command {
    private static final int MIN_CELLS_COLUMNS = 4;
    private static final int MIN_CELLS_ROWS = 4;
    private static final int MAX_CELLS_COLUMNS = 9;
    private static final int MAX_CELLS_ROWS = 11;

    public Init() {}
    public void execute(String[] args, FieldPanel context) throws InvalidArgumentException {
        if (args == null || args.length != 2 && args.length != 4) {
            throw new InvalidArgumentException(Init.class.getSimpleName().toUpperCase());
        }
        checkIntArguments(Init.class.getSimpleName().toUpperCase(), args);

        if (context.getTurtle() != null) {
            context.clear();
            context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
        }

        if (args.length == 2) {
            context.initCells(MAX_CELLS_COLUMNS, MAX_CELLS_ROWS);
            context.putTurtle(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }

        if (args.length == 4) {
            int columns = Integer.parseInt(args[0]);
            int rows = Integer.parseInt(args[1]);
            if (columns < MIN_CELLS_COLUMNS || columns > MAX_CELLS_COLUMNS || rows < MIN_CELLS_ROWS
                    || rows > MAX_CELLS_ROWS) {
                columns = MAX_CELLS_COLUMNS;
                rows = MAX_CELLS_ROWS;
            }
            context.initCells(columns, rows);
            context.putTurtle(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        }
        context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
    }
}
class Move extends Command {
    enum Direction { U, D, R, L }
    private char validateDirection(String arg) throws InvalidArgumentException {
        try {
            Direction direction = Direction.valueOf(arg);
            return direction.toString().charAt(0);
        } catch (IllegalArgumentException ex) {
            throw new InvalidArgumentException(Move.class.getSimpleName().toUpperCase());
        }
    }

    public Move() {}
    public void execute(String[] args, FieldPanel context) throws LogoWorldException {
        if (context.getTurtle() == null) {
            throw new NotInitializedException();
        }
        if (args == null || args.length != 2) {
            throw new InvalidArgumentException(Move.class.getSimpleName().toUpperCase());
        }
        checkIntArguments(Move.class.getSimpleName().toUpperCase(), new String[] { args[1] });
        validateDirection(args[0]);

        int length = Integer.parseInt(args[1]);
        char direction = validateDirection(args[0]);

        for (int i = 0; i < length; i++) {
            context.moveTurtle(direction);
            context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
        }
    }
}
class Draw extends Command {
    public Draw() {}
    public void execute(String[] args, FieldPanel context) throws LogoWorldException {
        if (context.getTurtle() == null) {
            throw new NotInitializedException();
        }
        if (args != null) {
            throw new InvalidArgumentException(Draw.class.getSimpleName().toUpperCase());
        }
        context.getTurtle().putDownPen();
        context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
    }
}
class Ward extends Command {
    public Ward() {}
    public void execute(String[] args, FieldPanel context) throws LogoWorldException {
        if (context.getTurtle() == null) {
            throw new NotInitializedException();
        }
        if (args != null) {
            throw new InvalidArgumentException(Ward.class.getSimpleName().toUpperCase());
        }

        context.getTurtle().putUpPen();
        context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
    }
}
class Teleport extends Command {
    public Teleport() {}
    public void execute(String[] args, FieldPanel context) throws LogoWorldException {
        if (context.getTurtle() == null) {
            throw new NotInitializedException();
        }
        if (args == null || args.length != 2) {
            throw new InvalidArgumentException(Teleport.class.getSimpleName().toUpperCase());
        }

        context.putTurtle(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
    }
}

public class CommandFactory {
    private static final String RSC_NAME = "config.csv";
    private static final String PACKAGE_NAME = "app.logoworld.api.";

    private static Map<String, String> config = new TreeMap<>();
    private static Map<String, Command> cache = new TreeMap<>();

    static {
        InputStream inputstream = null;
        try {
            inputstream = CommandFactory.class.getResourceAsStream(RSC_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split(",\\s+");
                config.put(args[0], args[1]);
            }
        }
        catch (IOException | NullPointerException e) {
            System.err.println(e.getLocalizedMessage());
        }
        finally {
            if (inputstream != null) {
                try { inputstream.close(); }
                catch (IOException e) { System.err.println(e.getLocalizedMessage()); }
            }
        }
    }

    public static Command create(String stringCommand) throws InvalidCommandException {
        String commandClassName = config.get(stringCommand);
        if (commandClassName == null) {
            throw new InvalidCommandException(stringCommand);
        }
        if (cache.containsKey(commandClassName)) {
            return cache.get(commandClassName);
        }
        Command command = null;
        try {
            command = (Command)Class.forName(PACKAGE_NAME + commandClassName).getConstructor().newInstance();
            cache.put(commandClassName, command);
        }
        catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException
                | ClassNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return command;
    }
}
