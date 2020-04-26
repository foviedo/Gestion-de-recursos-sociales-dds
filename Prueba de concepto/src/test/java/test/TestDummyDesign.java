package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import domain.DummyDesign;

public class TestDummyDesign {

	DummyDesign dummy = new DummyDesign();

	@Test
	public void testIntegrante3() {

		assertEquals(3, dummy.integrante3());

	}

	@Test
	public void testIntegrante4() {
		assertEquals(4, dummy.integrante4());
	}

}
