package domain;
/*
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import domain.validacionDeEgresos.Validacion;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgregarEgresoADBTest implements WithGlobalEntityManager {
    private Egreso unEgreso;
    private Item item1;
    private Item item2;
    private LocalDateTime fechaDeOperacion;
    private List<Item> itemsA = new ArrayList<Item>();
    private Moneda unaMoneda;
    private int i = 1;
    private int j = 1;
    private Documento unDocumento;
    private MedioDePago unMedioDePago;

	@Before
	public void setup() {
		fechaDeOperacion = LocalDateTime.of(2020, 2, 2, 0, 0);
		unaMoneda = new Moneda("58", "moneda normal", "$");
		item1 = new Item("caja", unaMoneda, 140);
		item2 = new Item("bolsa", unaMoneda, 170);
		itemsA.add(item1);
		itemsA.add(item2);
		DireccionPostal direccionPostal = new DireccionPostal("122", "bar", "foo", "42", "juancito", "elsulo", "gaston", "lucasaprobanoseltp");
		Proveedor proveedor = new Proveedor("juan gomez", 15478, direccionPostal);
		List<String> etiquetas = new ArrayList<String>();
		etiquetas.add("chocho");
		etiquetas.add("casa");
		etiquetas.add("stonks");
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("pupu", "VeryDificult!021"));
		usuarios.add(new Usuario("papa", "VeryDificult!022"));
		usuarios.add(new Usuario("pepe", "VeryDificult!023"));
		usuarios.add(new Usuario("pipo", "VeryDificult!024"));
		unDocumento = new Documento("cheque", 1251);
		unMedioDePago = new MedioDePago(TipoMedioDePago.DINERO_EN_CUENTA, "214");
		unEgreso = new Egreso(unDocumento, unMedioDePago, proveedor, fechaDeOperacion, itemsA, usuarios, Validacion.VALIDAR_CRITERIO_PROVEEDOR, etiquetas);
		unEgreso.cargarPresupuesto("presupuesto", itemsA);
	}

	@Test
	public void persistirEgreso() {
		RepositorioEgresos.getInstance().agregarEgreso(unEgreso);
		Assert.assertEquals(i, j);
	}
}

*/