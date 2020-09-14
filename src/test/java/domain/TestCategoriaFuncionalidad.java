package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.FuncionalidadException;

import static org.mockito.Mockito.mock;

import java.awt.List;
import java.util.ArrayList;


public class TestCategoriaFuncionalidad {
	Egreso unEgreso1;
	Juridica unaEntidadJuridica;
	Categoria categoriaSinRestricciones;
	Categoria categoriaConRestricciones;
	Categoria categoriaConRestriccionParaBase;
	Base unaEntidadBaseSinNada;
	Base unaEntidadBaseRestringida;
	MontoEgresosSuperado restriccion1;
	NoPuedoAgregarEntidadesBase restriccion2;
	NoPuedoSerAgregada restriccion3;
	
	
	@Before
	public void setup() {
		unEgreso1 = mock(Egreso.class);
		unaEntidadJuridica = new Juridica("yo","tuvieja",27,"calle falsa 123",8);
		categoriaSinRestricciones = new Categoria(new ArrayList<Funcionalidad>(),"categoriaSinRestricciones");
		unaEntidadBaseSinNada = new Base("mi nombre","no se que va aca");
		categoriaConRestricciones= new Categoria(new ArrayList<Funcionalidad>(),"categoriaConRestricciones");
		restriccion1 = new MontoEgresosSuperado(2);
		restriccion2 = new NoPuedoAgregarEntidadesBase();
		unaEntidadBaseRestringida = new Base("nombre","bro");
		categoriaConRestriccionParaBase = new Categoria(new ArrayList<Funcionalidad>(),"categoriaConRestriccionParaBase");
		restriccion3 = new NoPuedoSerAgregada();
		categoriaConRestriccionParaBase.agregarFuncionalidad(restriccion3);
		unaEntidadBaseRestringida.setCategoria(categoriaConRestriccionParaBase);
		
	}
	
	@Test
	public void agregarEgresoAVacia() {
		unaEntidadJuridica.setCategoria(categoriaSinRestricciones);
		unaEntidadJuridica.agregarEgreso(unEgreso1);
		assertEquals(1,unaEntidadJuridica.egresos.size());
	}
	@Test
	public void agregarBaseACatVacia() {
		unaEntidadJuridica.setCategoria(categoriaSinRestricciones);
		unaEntidadJuridica.agregarEntidadBase(unaEntidadBaseSinNada);
		assertEquals(1,unaEntidadJuridica.listaDeEntidadesBase.size());
	}
	@Test
	public void agregarDosEgresoAlQueTieneRestriccion1() {
		categoriaConRestricciones.agregarFuncionalidad(restriccion1);
		unaEntidadJuridica.setCategoria(categoriaConRestricciones);
		unaEntidadJuridica.agregarEgreso(unEgreso1);
		unaEntidadJuridica.agregarEgreso(unEgreso1);
		assertEquals(2,unaEntidadJuridica.egresos.size());
	}
	@Test(expected = FuncionalidadException.class)
	public void agregarTresEgresosAlQueTieneRestriccion1() {
		categoriaConRestricciones.agregarFuncionalidad(restriccion1);
		unaEntidadJuridica.setCategoria(categoriaConRestricciones);
		unaEntidadJuridica.agregarEgreso(unEgreso1);
		unaEntidadJuridica.agregarEgreso(unEgreso1);
		unaEntidadJuridica.agregarEgreso(unEgreso1);
	}
	@Test(expected = FuncionalidadException.class)
	public void agregarEntidadBaseSaleMal() {
		categoriaConRestricciones.agregarFuncionalidad(restriccion2);
		unaEntidadJuridica.setCategoria(categoriaConRestricciones);
		unaEntidadJuridica.agregarEntidadBase(unaEntidadBaseSinNada);
	}
	@Test(expected = FuncionalidadException.class)
	public void agregarBaseRestringida() {
		unaEntidadJuridica.agregarEntidadBase(unaEntidadBaseRestringida);
	}
	
	

}
