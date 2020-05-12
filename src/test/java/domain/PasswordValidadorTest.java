package domain;
import exception.PasswordInvalidoException;
import org.junit.Test;

public class PasswordValidadorTest {
    @Test(expected = PasswordInvalidoException.class)
    public void cuandoElPasswordEsComunDeberiaLanzarUnaException() {
        Usuario usuario = new Usuario("juan20", "asdf");
        ValidadorPassword validadorPassword = new ValidadorPassword();
        PasswordComunValidador passwordComunValidador = new PasswordComunValidador();
        validadorPassword.addValidador(passwordComunValidador);
        validadorPassword.validarUsuario(usuario);
    }

    @Test(expected = PasswordInvalidoException.class)
    public void cuandoElPasswordEsDerivadoDelUsuarioDeberiaLanzarUnaException() {
        Usuario usuario = new Usuario("juan20", "juan2020");
        ValidadorPassword validadorPassword = new ValidadorPassword();
        PasswordDerivadoValidador passwordDerivadoValidador = new PasswordDerivadoValidador();
        validadorPassword.addValidador(passwordDerivadoValidador);
        validadorPassword.validarUsuario(usuario);
    }

    @Test(expected = PasswordInvalidoException.class)
    public void cuandoElPasswordEsRepetitivoDeberiaLanzarUnaException() {
        Usuario usuario = new Usuario("juan", "1111");
        ValidadorPassword validadorPassword = new ValidadorPassword();
        PasswordRepetitivoValidador passwordRepetitivoValidador = new PasswordRepetitivoValidador();
        validadorPassword.addValidador(passwordRepetitivoValidador);
        validadorPassword.validarUsuario(usuario);
    }

    @Test(expected = PasswordInvalidoException.class)
    public void cuandoElPasswordNoEsRobustoDeberiaLanzarUnaException() {
        Usuario usuario = new Usuario("juan", "michi123");
        ValidadorPassword validadorPassword = new ValidadorPassword();
        PasswordRobustoValidador passwordRobustoValidador = new PasswordRobustoValidador();
        validadorPassword.addValidador(passwordRobustoValidador);
        validadorPassword.validarUsuario(usuario);
    }

    @Test
    public void cuandoElPasswordPasaLasValidacionesNoDeberiaLanzarUnaException() {
        Usuario usuario = new Usuario("juan", "VeryDificult!020");
        ValidadorPassword validadorPassword = new ValidadorPassword();
        PasswordComunValidador passwordComunValidador = new PasswordComunValidador();
        PasswordDerivadoValidador passwordDerivadoValidador = new PasswordDerivadoValidador();
        PasswordRepetitivoValidador passwordRepetitivoValidador = new PasswordRepetitivoValidador();
        PasswordRobustoValidador passwordRobustoValidador = new PasswordRobustoValidador();
        validadorPassword.addValidador(passwordComunValidador);
        validadorPassword.addValidador(passwordDerivadoValidador);
        validadorPassword.addValidador(passwordRepetitivoValidador);
        validadorPassword.addValidador(passwordRobustoValidador);
        validadorPassword.validarUsuario(usuario);
    }
}
