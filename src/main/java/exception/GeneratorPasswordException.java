package exception;

public class GeneratorPasswordException extends RuntimeException {
    public GeneratorPasswordException(String message) {
        super(message);
    }

    public GeneratorPasswordException(String message, Exception e) {
        super(message, e);
    }
}
