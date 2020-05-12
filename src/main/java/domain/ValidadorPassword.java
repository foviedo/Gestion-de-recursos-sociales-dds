package domain;

import exception.PasswordInvalidoException;
import java.util.ArrayList;
import java.util.List;

public class ValidadorPassword {
    private List<Validador> validadores;

    public ValidadorPassword() {
        this.validadores = new ArrayList<>();
    }

    public void validarUsuario(Usuario usuario) {
        if (validadores.stream().anyMatch(validadorPassword -> validadorPassword.noEsValido(usuario))) {
            throw new PasswordInvalidoException();
        }
    }

    public void addValidador(Validador validador) {
        validadores.add(validador);
    }

    public void removeValidador(Validador validador) {
        validadores.remove(validador);
    }
}
