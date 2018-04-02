package app.logoworld.exception;

public class NotInitializedException extends LogoWorldException {

    public NotInitializedException() {
        this.title = "The LogoWorld must be initialized";
        this.msg =
                "<html>" +
                    "The LogoWorld must be initialized!" +
                    "<br/>" +
                    "Please, try to begin with the command: " +
                    "<br/><br/>" +
                        commandSyntax.get("INIT") +
                    "<br/><br/>" +
                "</html>";
    }

}
