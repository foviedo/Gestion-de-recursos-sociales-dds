package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PresupuestoTest {
	Item item1;
	Item item2;
	Item item3;
	Item item4;
	Moneda unaMoneda;
	Egreso egresoQueUsaLaMasBarata;
	Egreso egresoQueUsaLaMasCara;
	Documento unDocumento;
	MedioDePago unMedioDePago;
	DireccionPostal unaDireccionPostal;
	Proveedor unProveedor;
	LocalDateTime unaFecha;
	List<Item> items1;
	List<Item> items2;
	Usuario usuario1;
	List<Usuario> revisores1;
	Validacion criterioProveedores;
	List<String> etiquetas = new ArrayList<>();
	
	@Before
	public void setup() {
		unaMoneda = new Moneda();
		item1 = new Item("desc1",unaMoneda, 10);
		item2 = new Item("desc2",unaMoneda, 20);
		item3 = new Item("desc3",unaMoneda, 30);
		item4 = new Item("desc4",unaMoneda, 40);
		unDocumento = new Documento("factura",123);
		unMedioDePago = new MedioDePago(TipoMedioDePago.DINERO_EN_CUENTA,"127");
		unaDireccionPostal = mock(DireccionPostal.class);
		unProveedor = new Proveedor("unNombre",345,unaDireccionPostal);
		unaFecha = LocalDateTime.of(2020, 2, 2, 0, 0);
		items1 = new ArrayList<Item>();
		items1.add(item1);
		items1.add(item2);
		items2 = new ArrayList<Item>();
		items2.add(item3);
		items2.add(item4);
		usuario1 = new Usuario("user","VeryDificult!020");
		revisores1 = new ArrayList<Usuario>();
		revisores1.add(usuario1);
		criterioProveedores = Validacion.VALIDAR_CRITERIO_PROVEEDOR;
		egresoQueUsaLaMasBarata = new Egreso(unDocumento, unMedioDePago, unProveedor, unaFecha, items1, revisores1,criterioProveedores, etiquetas);
		egresoQueUsaLaMasCara = new Egreso(unDocumento, unMedioDePago, unProveedor, unaFecha, items2, revisores1, criterioProveedores,etiquetas);
	}
	
	@Test
	public void menosPresupuestosDeLosRequeridos() {
		egresoQueUsaLaMasBarata.validarme();
		assertEquals(EstadoEgreso.INVALIDO,usuario1.bandejaDeEntrada.get(0).estadoValidacion);
	}
	
	@Test
	public void laCompraNoFueEnBaseAUnoDeLosPresupuestos() {
		egresoQueUsaLaMasBarata.cargarPresupuesto("detalle we", items2);
		egresoQueUsaLaMasBarata.cargarPresupuesto("detalle2 we", items2);
		egresoQueUsaLaMasBarata.validarme();
		assertEquals(EstadoEgreso.INVALIDO,usuario1.bandejaDeEntrada.get(0).estadoValidacion);
	}
	
	@Test
	public void laCompraNoFueLaMasBarata() {
		egresoQueUsaLaMasCara.cargarPresupuesto("detalle we", items1);
		egresoQueUsaLaMasCara.cargarPresupuesto("detalle2 we", items2);
		egresoQueUsaLaMasCara.validarme();
		assertEquals(EstadoEgreso.INVALIDO,usuario1.bandejaDeEntrada.get(0).estadoValidacion);
	}
	
	@Test
	public void egresoValido() {
		egresoQueUsaLaMasBarata.cargarPresupuesto("detalle we", items1);
		egresoQueUsaLaMasBarata.cargarPresupuesto("detalle2 we", items2);
		egresoQueUsaLaMasBarata.validarme();
		assertEquals(EstadoEgreso.VALIDO,usuario1.bandejaDeEntrada.get(0).estadoValidacion);
	}

}
