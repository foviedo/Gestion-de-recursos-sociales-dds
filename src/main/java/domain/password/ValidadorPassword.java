package domain.password;

import java.util.Arrays;
import java.util.List;

public class ValidadorPassword {
    private final List<Validador> validadoresPassword;

    public ValidadorPassword() {
        this.validadoresPassword = Arrays.asList(new PasswordComunValidador(),
                                         new PasswordDerivadoValidador(),
                                         new PasswordRepetitivoValidador(),
                                         new PasswordRobustoValidador());
    }

    public boolean esValido(String usuario, String password) {
        return validadoresPassword.stream().allMatch(validadorPassword -> validadorPassword.esValido(usuario, password));
    }
}
