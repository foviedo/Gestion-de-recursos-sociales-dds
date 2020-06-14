package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Egreso {
	Documento documentoComercial;
	MedioDePago medioDePago;
	Proveedor proveedor;
	LocalDate fechaDeOperacion;
	Entidad destinatario;
	List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	static final int cantidadPresupuestos = 2;
	List<Usuario> revisores;
	EstadoEgreso estadoValidacion;

	List<Item> listaDeItems = new ArrayList<Item>();

	public Egreso(Documento documentoComercial, MedioDePago medioDePago, Proveedor proveedor,
			LocalDate fechaDeOperacion, List<Item> listaDeItems) {
		this.documentoComercial = documentoComercial;
		this.medioDePago = medioDePago;
		this.proveedor = proveedor;
		this.fechaDeOperacion = fechaDeOperacion;
		this.listaDeItems = listaDeItems;
	}

	List<Item> getListaDeItems() {
		return listaDeItems;
	}

	List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	void cargarPresupuesto(String detalle, List<Item> listaDeItems) { // Misma lista Egreso-Presupuesto?

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

	void enviarResultadoACadaUsuario() {
		revisores.forEach((unRevisor) -> {
			unRevisor.agrerarResultado(this);
		});
	}

}
