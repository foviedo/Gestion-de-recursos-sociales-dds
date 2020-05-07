package exception;

public class PasswordInvalidoException extends RuntimeException {
    public PasswordInvalidoException() {
        super("Password inválido");
    }
}
