package domain.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordRobustoValidador implements Validador {
    @Override
    public boolean esValido(String nombreUsuario, String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
