package domain;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestsOrganizaciones {
	private DireccionPostal unaDireccion = new DireccionPostal();
	private TipoEmpresa tipoE;
	private Empresa samsung;
	private Egreso unEgreso;
	private Documento unDocumento;
	private MedioDePago unaTarjeta;
	private TarjetaDeDebito laTarjeta;
	private LocalDate fechaOperacion; 
	private List<Item> items = new ArrayList<Item>();
	private Proveedor unProveedor = new Proveedor("carlos",0214,unaDireccion);
	private List<Entidad> entidades = new ArrayList<Entidad>();
	@Before
	public void init() {
		 tipoE = new Tramo1();
		 samsung = new Empresa("a", "n", 1, "c", 2,entidades, tipoE);
		 unDocumento = new Documento ("factura",1231);
		 laTarjeta = new TarjetaDeDebito("5");
		 unaTarjeta = laTarjeta;
		 fechaOperacion = LocalDate.now();
		 unEgreso = new Egreso(unDocumento, unaTarjeta, unProveedor ,fechaOperacion,items);
		 
	}

	@Test
	public void test() {
		assertTrue(samsung.getTipoEmpresa().prueba());
	}
	@Test
	public void testEgreso() {
		assertTrue(unEgreso.prueba());
	}
	@Test
	public void testTarjeta() {
		assertTrue(unEgreso.medioDePago.prueba());
	}
	

}
