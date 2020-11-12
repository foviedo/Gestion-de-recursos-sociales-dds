package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;
import domain.validacionDeEgresos.Validacion;
import domain.validacionDeEgresos.ValidadorDeEgresos;
import javax.persistence.*;

@Entity
public class Egreso extends PersistentEntity {
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "id_documento")
	Documento documentoComercial;
	@ManyToOne(cascade=CascadeType.PERSIST)
	MedioDePago medioDePago;
	@ManyToOne(cascade=CascadeType.PERSIST)
	Proveedor proveedor;
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime fechaDeOperacion;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_egreso")
	List<Presupuesto> presupuestos = new ArrayList<>();
	private static Integer cantidadPresupuestosNecesarios = 2;
	@ManyToMany(cascade=CascadeType.PERSIST)
	List<Usuario> revisores;
	@Enumerated(EnumType.STRING)
	EstadoEgreso estadoValidacion;
	@Enumerated(EnumType.STRING)
	Validacion criterio;
	@Transient
	ValidadorDeEgresos validador;
	@ElementCollection
	List<String> etiquetas;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_egreso")
	List<Item> listaDeItems;

	public Egreso(Documento documentoComercial, MedioDePago medioDePago, Proveedor proveedor,
			LocalDateTime fechaDeOperacion, List<Item> listaDeItems, List<Usuario> listaDeRevisores,
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
		this.etiquetas = etiquetas;
	}
	public Egreso() {
		//no se si hay que instanciar el validador
	};
	
	
	List<String> getEtiquetas(){
		return etiquetas;
	}

	void agregarEtiqueta(String unaEtiqueta) {
		etiquetas.add(unaEtiqueta);
	}
	
	public List<Item> getListaDeItems() {
		return listaDeItems;
	}

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	void cargarPresupuesto(String detalle, List<Item> listaDeItems) { 

		Presupuesto unPresupuesto = new Presupuesto(detalle, listaDeItems);
		presupuestos.add(unPresupuesto);
	}

	public double getMontoTotal() {
		return listaDeItems.stream().mapToDouble(item -> item.getMonto()).sum();
	}
	
	public Documento getDocumentoComercial() {
		return documentoComercial;
	}
	
	public MedioDePago getMedioDePago() {
		return medioDePago;
	}
	
	public EstadoEgreso getEstadoValidacion() {
		return estadoValidacion;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	boolean esDe(Usuario unUsuario) {
		return revisores.contains(unUsuario);
	}

	public void setEstadoValidacion(EstadoEgreso unEstado) {
		estadoValidacion = unEstado;
	}

	public void enviarResultadoACadaUsuario() {
		revisores.forEach(unRevisor -> {
			unRevisor.agregarResultado(this);
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
	
	public boolean contieneEtiqueta(String etiqueta) {
		return etiquetas.contains(etiqueta);
	}
}
