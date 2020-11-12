package domain.password;

public interface Validador {
    boolean esValido(String nombreUsuario, String password);
}