package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordRepetitivoValidador implements Validador {
    @Override
    public Boolean noEsValido(Usuario usuario) {
        Pattern pattern = Pattern.compile("(.)\\1{2}");
        Matcher matcher = pattern.matcher(usuario.getPassword());
        return matcher.find();
    }
}
