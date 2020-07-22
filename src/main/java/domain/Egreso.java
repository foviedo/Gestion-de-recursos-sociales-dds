package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Egreso {
	Documento documentoComercial;
	MedioDePago medioDePago;
	Proveedor proveedor;
	LocalDate fechaDeOperacion;
	List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	private static Integer cantidadPresupuestosNecesarios = 2;
	List<Usuario> revisores;
	EstadoEgreso estadoValidacion;
	Validacion criterio;
	ValidadorDeEgresos validador;
	
	List<String> etiquetas = new ArrayList<String>();
	List<Item> listaDeItems = new ArrayList<Item>();

	public Egreso(Documento documentoComercial, MedioDePago medioDePago, Proveedor proveedor,
			LocalDate fechaDeOperacion, List<Item> listaDeItems, List<Usuario> listaDeRevisores,
			Validacion criterio, List<String> etiquetas) {
		this.documentoComercial = documentoComercial;
		this.medioDePago = medioDePago;
		this.proveedor = proveedor;
		this.fechaDeOperacion = fechaDeOperacion;
		this.listaDeItems = listaDeItems;
		this.revisores = listaDeRevisores;
		this.estadoValidacion = EstadoEgreso.SIN_VALIDAR;
		this.criterio = criterio;
		this.validador = new ValidadorDeEgresos(criterio);
		if(etiquetas.isEmpty()) {
			this.etiquetas.add("sin etiqueta");
		} else {
			this.etiquetas = etiquetas;
		}
	}

	void agregarEtiqueta(String unaEtiqueta) {
		if(etiquetas.contains("sin etiqueta")) {
			etiquetas.remove(0);
		}
		etiquetas.add(unaEtiqueta);
	}
	
	List<Item> getListaDeItems() {
		return listaDeItems;
	}

	List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	void cargarPresupuesto(String detalle, List<Item> listaDeItems) { 

		Presupuesto unPresupuesto = new Presupuesto(detalle, listaDeItems);
		presupuestos.add(unPresupuesto);
	}

	public double getMontoTotal() {
		return listaDeItems.stream().mapToDouble(item -> item.getMonto()).sum();
	}

	boolean esDe(Usuario unUsuario) {
		return revisores.contains(unUsuario);
	}

	void setEstadoValidacion(EstadoEgreso unEstado) {
		estadoValidacion = unEstado;
	}

	public void enviarResultadoACadaUsuario() {
		revisores.forEach((unRevisor) -> {
			unRevisor.agrerarResultado(this);
		});
	}
	
	public void agregarRevisor(Usuario unUsuario) {
		revisores.add(unUsuario);
	}

	public Boolean tenesLosPresupuestosSuficientes() {
		return cantidadPresupuestosNecesarios == presupuestos.size();
	}
	public static void modificarCantidadPresupuestosNecesarios(int unaCant) {
		cantidadPresupuestosNecesarios = unaCant;
	}
	public void validarme() {
		this.validador.validar(this);
	}
	public boolean estoySinValidar() {
		return estadoValidacion == EstadoEgreso.SIN_VALIDAR;
	}
	
	boolean contengoEtiqueta(String etiqueta) {
		return etiquetas.contains(etiqueta);
	}
}
