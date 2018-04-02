package app.logoworld.api;

import app.logoworld.view.field.FieldEvent;
import app.logoworld.view.field.FieldPanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

// Command == Product
abstract class Command {
    public abstract void execute(String[] args, FieldPanel context);
}

class Init extends Command {
    private static final int MIN_CELLS_COLUMNS = 4;
    private static final int MIN_CELLS_ROWS = 4;
    private static final int MAX_CELLS_COLUMNS = 9;
    private static final int MAX_CELLS_ROWS = 11;

    public Init() {}
    public void execute(String[] args, FieldPanel context) {
        // TODO: if (args.length < 2) throw new Exception();
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
    public Move() {}
    public void execute(String[] args, FieldPanel context) {
        // TODO if (args.length < 2) throw new Exception();
        int length = Integer.parseInt(args[1]);
        // TODO if (length < 0) throw new Exception();
        char direction = args[0].toUpperCase().charAt(0);
        // TODO if (direction == null) throw new Exception();

        for (int i = 0; i < length; i++) {
            context.moveTurtle(direction);
            context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
        }
    }
}
class Draw extends Command {
    public Draw() {}
    public void execute(String[] args, FieldPanel context) {
        // TODO if (args.length > 0) throw Exception;
        context.getTurtle().putDownPen();
        context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
    }
}
class Ward extends Command {
    public Ward() {}
    public void execute(String[] args, FieldPanel context) {
        // TODO if (args.length > 0) throw Exception;
        context.getTurtle().putUpPen();
        context.fireFieldEvent(new FieldEvent(context, context.getTurtle()));
    }
}
class Teleport extends Command {
    public Teleport() {}
    public void execute(String[] args, FieldPanel context) {
        // TODO if (args.length < 2) throw Exception;
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
                // TODO проверка корректности конфига
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

    public static Command create(String stringCommand) {
        // TODO: throw new IllegalCommandException();
        String commandClassName = config.get(stringCommand);
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
