package domain;

import exception.PasswordInvalidoException;
import java.util.List;

public class Usuario {
    private String usuario;
    private String password;
    private List<ValidadorPassword> validadorPasswords;

    public Usuario(String usuario, String password) {
        validarPassword(password);
        this.usuario = usuario;
        this.password = password;
    }

    private void validarPassword(String password) {
        if (!validadorPasswords.stream().allMatch(validadorPassword -> validadorPassword.esValida(password))) {
            throw new PasswordInvalidoException();
        }
    }

    public void addValidadorPassword(ValidadorPassword validadorPassword) {
        validadorPasswords.add(validadorPassword);
    }
}
