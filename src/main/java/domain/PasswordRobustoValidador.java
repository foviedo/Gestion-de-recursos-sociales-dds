package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordRobustoValidador implements Validador {
    @Override
    public Boolean noEsValido(Usuario usuario) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$");
        Matcher matcher = pattern.matcher(usuario.getPassword());
        return !matcher.find();
    }
}
