package by.epam.goalplanner.exception;

public class JspException extends Exception{
    public JspException() {
    }

    public JspException(String message) {
        super(message);
    }

    public JspException(String message, Throwable cause) {
        super(message, cause);
    }

    public JspException(Throwable cause) {
        super(cause);
    }
}
