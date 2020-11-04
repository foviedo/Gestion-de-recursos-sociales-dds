package domain.password;

public interface ValidadorPassword {
    Boolean esValido(String nombreUsuario, String password);
}