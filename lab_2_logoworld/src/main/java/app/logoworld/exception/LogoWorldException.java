package app.logoworld.exception;


import java.util.Map;
import java.util.TreeMap;

abstract public class LogoWorldException extends Exception implements ExceptionCommons {

    protected static Map<String, String> commandSyntax = new TreeMap<>();
    protected String msg;
    protected String title;

    static {
        commandSyntax.put("INIT", "\"INIT [int width]? [int height]? [int x] [int y]\"");
        commandSyntax.put("MOVE", "\"MOVE [char dir] [int shift]\"");
        commandSyntax.put("DRAW", "\"DRAW\"");
        commandSyntax.put("WARD", "\"WARD\"");
        commandSyntax.put("TELEPORT", "\"TELEPORT [int x] [int y]\"");
    }

    public String getMessage() {
        return this.msg;
    }

    public String getTitle() {
        return this.title;
    }

}
