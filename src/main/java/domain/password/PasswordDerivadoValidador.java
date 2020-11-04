package domain.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordDerivadoValidador implements ValidadorPassword {
    @Override
    public Boolean esValido(String nombreUsuario, String password) {
        Pattern pattern = Pattern.compile(String.format("(?i).*(%s).*", nombreUsuario));
        Matcher matcher = pattern.matcher(password);
        return !matcher.find();
    }
}
