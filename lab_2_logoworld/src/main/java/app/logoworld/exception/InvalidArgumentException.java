package app.logoworld.exception;


public class InvalidArgumentException extends LogoWorldException {

    public InvalidArgumentException(String commandName) {
        this.title = "Invalid arguments";
        this.msg =
                "<html>" +
                        "Invalid arguments for the command " +
                        "<b>" +
                            "\"" + commandName + "\"" +
                        "</b>" +
                        "<br/>" +
                "<html>";
    }
}
