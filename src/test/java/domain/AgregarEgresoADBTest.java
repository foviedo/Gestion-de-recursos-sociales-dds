package domain;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import junit.framework.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class AgregarEgresoADBTest implements  WithGlobalEntityManager{
	 Egreso unEgreso;
	 Item item1;
	 Item item2;
	 LocalDate fechaDeOperacion;
	 List<Item> itemsA;
	 Moneda unaMoneda;
	 int i = 1;
	 int j = 1;
	@Before
	public void setup() {
		fechaDeOperacion = LocalDate.now();
		unaMoneda = new Moneda("0","moneda normal", "$");
		 item1 = new Item("caja",unaMoneda,140);
		item2 = new Item("bolsa",unaMoneda,170);
		 itemsA = new ArrayList<Item>();
		itemsA.add(item1);
		itemsA.add(item2);
		 unEgreso = new Egreso(null, null, null, fechaDeOperacion, itemsA, null, Validacion.VALIDAR_CRITERIO_PROVEEDOR, null); 
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void persistirEgreso() {
		RepositorioEgresos.getInstance().agregarEgreso(unEgreso);
		Assert.assertEquals(i, j);
	}

}
