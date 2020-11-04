package domain.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordRepetitivoValidador implements ValidadorPassword {
    @Override
    public Boolean esValido(String nombreUsuario, String password) {
        Pattern pattern = Pattern.compile("(.)\\1{2}");
        Matcher matcher = pattern.matcher(password);
        return !matcher.find();
    }
}
