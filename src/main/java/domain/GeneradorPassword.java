package domain;

import exception.GeneratorPasswordException;
import java.util.Arrays;
import java.util.List;

public class GeneradorPassword {
    private final GeneradorHashing generadorHashing;
    private final List<ValidadorPassword> validadoresPassword;

    public GeneradorPassword() {
        this.generadorHashing = new GeneradorHashing();
        this.validadoresPassword = Arrays.asList(new PasswordComunValidador(),
                                         new PasswordDerivadoValidador(),
                                         new PasswordRepetitivoValidador(),
                                         new PasswordRobustoValidador());
    }

    public byte[] encriptarPassword(String nombreUsuario, String password) {
        if (esValido(nombreUsuario, password)) {
            return generadorHashing.encriptarPassword(password);
        } else {
            throw new GeneratorPasswordException("Password inválido");
        }
    }

    private Boolean esValido(String nombreUsuario, String password) {
        return validadoresPassword.stream().allMatch(validadorPassword -> validadorPassword.esValido(nombreUsuario, password));
    }
}
