package app.logoworld;

import app.logoworld.state.PlayingField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

// Command == Product
abstract class Command {
    protected static PlayingField playing_field = new PlayingField(10,10, 0, 0);
    void execute(Object[] args) {}
}

class Init extends Command {
    public void execute(Object[] args) {
        // TODO if (args.length < 4) throw Exception;
        playing_field = new PlayingField((int)args[0], (int)args[1], (int)args[2], (int)args[3]);
    }
}
class Move extends Command {
    public void execute(Object[] args) {
        // TODO if (args.length < 2) throw Exception;
        char direction = (char)args[0];
        int length = (int)args[1];
        // TODO if (length < 0) throw Exception;
        playing_field.getTurtleState().move(direction, length);
    }
}
class Draw extends Command {
    public void execute(Object[] args) {
        // TODO if (args.length > 0) throw Exception;
        playing_field.getTurtleState().putDownPen();
    }
}
class Ward extends Command {
    public void execute(Object[] args) {
        // TODO if (args.length > 0) throw Exception;
        playing_field.getTurtleState().putUpPen();
    }
}
class Teleport extends Command {
    public void execute(Object[] args) {
        // TODO if (args.length < 2) throw Exception;
        playing_field.getTurtleState().changeCoordinates((int)args[0], (int)args[1]);
    }
}

public class CommandFactory {
    private static final String RSC_NAME = "config.csv";
    private static Map<String, String> config;
    private static Map<String, Command> cache;

    static {
        InputStream inputstream = null;
        try {
            inputstream = CommandFactory.class.getResourceAsStream(RSC_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\s+");
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
        String commandClassName = config.get(stringCommand);
        if (cache.containsKey(commandClassName)) {
            return cache.get(commandClassName);
        }
        Command command = null;
        try {
            command = (Command)Class.forName("app.logoworld" + commandClassName).getConstructor().newInstance();
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
