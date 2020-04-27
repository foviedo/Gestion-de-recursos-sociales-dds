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
	public void testIntegrante2() {
		assertEquals(2, dummy.integrante2());
	}
	@Test
	public void testIntegrante4() {
		assertEquals(4, dummy.integrante4());
	}
	@Test
	public void testIntegrante5() {
		assertEquals(5, dummy.integrante5());
	}
	@Test
	public void testIntegrante6() { assertEquals(6, dummy.integrante6()); }

}
