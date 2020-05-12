package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordDerivadoValidador implements Validador {
    @Override
    public Boolean noEsValido(Usuario usuario) {
        Pattern pattern = Pattern.compile(String.format("(?i).*(%s).*", usuario.getUsuario()));
        Matcher matcher = pattern.matcher(usuario.getPassword());
        return matcher.find();
    }
}
