package exception;

public class PasswordInvalidoException extends RuntimeException {
    private static final String message = "El password que desea almacenar el invalido";
    public PasswordInvalidoException() {
        super(message);
    }
}
