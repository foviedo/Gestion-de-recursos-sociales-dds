package domain;

import exception.GeneratorPasswordException;
import org.junit.Test;

public class GeneradorPasswordTest {
    @Test(expected = GeneratorPasswordException.class)
    public void cuandoElPasswordEsComunDeberiaLanzarUnaException() {
        new Usuario("juan20", "asdf");
    }

    @Test(expected = GeneratorPasswordException.class)
    public void cuandoElPasswordEsDerivadoDelUsuarioDeberiaLanzarUnaException() {
        new Usuario("juan20", "juan2020");
    }

    @Test(expected = GeneratorPasswordException.class)
    public void cuandoElPasswordEsRepetitivoDeberiaLanzarUnaException() {
        new Usuario("juan", "1111");
    }

    @Test(expected = GeneratorPasswordException.class)
    public void cuandoElPasswordNoEsRobustoDeberiaLanzarUnaException() {
        new Usuario("juan", "michi123");
    }

    @Test
    public void cuandoElPasswordPasaLasValidacionesNoDeberiaLanzarUnaException() {
        new Usuario("juan", "VeryDificult!020");
    }
}
