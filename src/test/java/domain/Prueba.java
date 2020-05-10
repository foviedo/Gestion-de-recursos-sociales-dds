package domain;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Prueba {
	public TipoEmpresa tipoE;
	public Empresa samsung;

	@Before
	public void init() {
		 tipoE = new Tramo1();
		 samsung = new Empresa("a", "n", 1, "c", 2, tipoE);
	}

	@Test
	public void test() {
		assertTrue(samsung.getTipoEmpresa().prueba());
	}

}
