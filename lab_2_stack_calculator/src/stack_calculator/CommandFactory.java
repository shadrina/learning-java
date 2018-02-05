package stack_calculator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

// Command == Product
interface Command {
    public void execute(Object[] args);
}

class Push implements Command {
    public void execute(Object[] args) {

    }
}
class Pop implements Command {
    public void execute(Object[] args) {

    }
}
class Add implements Command {
    public void execute(Object[] args) {

    }
}
class Subtract implements Command {
    public void execute(Object[] args) {

    }
}
class Multiply implements Command {
    public void execute(Object[] args) {

    }
}
class Divide implements Command {
    public void execute(Object[] args) {

    }
}
class Sqrt implements Command {
    public void execute(Object[] args) {

    }
}
class Print implements Command {
    public void execute(Object[] args) {

    }
}
class Define implements Command {
    public void execute(Object[] args) {

    }
}

public class CommandFactory {
    private static Map<String, String> config;

    public static Command create(String string_command) {
        Command command = null;
        try {
            command = (Command)Class.forName("stack_calculator." + config.get(string_command)).getConstructor().newInstance();
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





