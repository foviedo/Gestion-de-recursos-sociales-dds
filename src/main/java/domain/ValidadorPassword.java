package domain;

public interface ValidadorPassword {
    Boolean esValido(String nombreUsuario, String password);
}