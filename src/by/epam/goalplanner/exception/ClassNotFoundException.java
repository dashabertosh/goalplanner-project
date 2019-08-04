package by.epam.goalplanner.exception;

public class ClassNotFoundException extends Exception {
    public ClassNotFoundException() {
    }

    public ClassNotFoundException(String message) {
        super(message);
    }

    public ClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassNotFoundException(Throwable cause) {
        super(cause);
    }
}
