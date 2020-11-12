package domain;

import domain.password.ValidadorPassword;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidadorPasswordTest {
    private ValidadorPassword validadorPassword;

    @Before
    public void setUp() {
        validadorPassword = new ValidadorPassword();
    }

    @Test
    public void cuandoElPasswordEsComunDeberiaLanzarUnaException() {
        Assert.assertFalse(validadorPassword.esValido("juan20", "asdf"));
    }

    @Test
    public void cuandoElPasswordEsDerivadoDelUsuarioDeberiaLanzarUnaException() {
        Assert.assertFalse(validadorPassword.esValido("juan20", "juan2020"));
    }

    @Test
    public void cuandoElPasswordEsRepetitivoDeberiaLanzarUnaException() {
        Assert.assertFalse(validadorPassword.esValido("juan", "1111"));
    }

    @Test
    public void cuandoElPasswordNoEsRobustoDeberiaLanzarUnaException() {
        Assert.assertFalse(validadorPassword.esValido("juan", "michi123"));
    }

    @Test
    public void cuandoElPasswordPasaLasValidacionesNoDeberiaLanzarUnaException() {
        Assert.assertTrue(validadorPassword.esValido("juan", "VeryDificult!020"));
    }
}
