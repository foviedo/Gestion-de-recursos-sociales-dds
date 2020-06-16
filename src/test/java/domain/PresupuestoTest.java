package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PresupuestoTest {
	Item item1;
	Item item2;
	Item item3;
	Item item4;
	Moneda unaMoneda;
	Egreso egreso1;
	Documento unDocumento;
	MedioDePago unMedioDePago;
	DireccionPostal unaDireccionPostal;
	Proveedor unProveedor;
	LocalDate unaFecha;
	List<Item> items1;
	Usuario usuario1;
	List<Usuario> revisores1;
	
	@Before
	public void setup() {
		unaMoneda = new Moneda();
		item1 = new Item("desc1",unaMoneda, 10);
		item2 = new Item("desc2",unaMoneda, 20);
		item3 = new Item("desc3",unaMoneda, 30);
		item4 = new Item("desc4",unaMoneda, 40);
		unDocumento = new Documento("factura",123);
		unMedioDePago = new MedioDePago();
		unaDireccionPostal = mock(DireccionPostal.class);
		unProveedor = new Proveedor("unNombre",345,unaDireccionPostal);
		unaFecha = LocalDate.of(1111,11,11);
		items1 = new ArrayList<Item>();
		items1.add(item1);
		usuario1 = new Usuario("user","VeryDificult!020");
		revisores1 = new ArrayList<Usuario>();
		revisores1.add(usuario1);
		egreso1 = new Egreso(unDocumento, unMedioDePago, unProveedor, unaFecha, items1, revisores1);
	}
	
	@Test
	public void menosPresupuestosDeLosRequeridos() {
		ValidadorDeEgresos miValidador = ValidadorDeEgresos.getInstance();
		miValidador.egresosPorValidar.add(egreso1);
		miValidador.validarEgresoPendientes();
		//assertEquals(EstadoEgreso.INVALIDO,egreso1.estadoValidacion);
		assertEquals(EstadoEgreso.INVALIDO,usuario1.bandejaDeEntrada.get(0).estadoValidacion);
	}

}
