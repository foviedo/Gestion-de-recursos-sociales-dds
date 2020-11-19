package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id_entidad_madre" )
public class Juridica extends Entidad {
	String razonSocial;
	int cuit;
	String direccionPostal;
	int codInscripcion;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "id_juridica")
	List<Base> listaDeEntidadesBase = new ArrayList<Base>();
	@Enumerated(EnumType.STRING)
	TipoJuridica tipoEntidadJuridica;

	public Juridica() {
		
	}
	public Juridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscripcion) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscripcion = codInscripcion;
	}
	
	void setTipoJuridica(TipoJuridica tipo) {
		this.tipoEntidadJuridica = tipo;
	}

	void agregarEntidadBase(Base unaEntidad){
		if (categoria != null) {
			categoria.agregarEntidadBase(this);

		}
		unaEntidad.serAgregada();
		listaDeEntidadesBase.add(unaEntidad);
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNombreFicticio() {
		return nombreFicticio;
	}
	public void setNombreFicticio(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}
	public int getCuit() {
		return cuit;
	}
	public void setCuit(int cuit) {
		this.cuit = cuit;
	}
	public String getDireccionPostal() {
		return direccionPostal;
	}
	public void setDireccionPostal(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}
	public int getCodInscripcion() {
		return codInscripcion;
	}
	public void setCodInscripcion(int codInscripcion) {
		this.codInscripcion = codInscripcion;
	}
	public List<Base> getListaDeEntidadesBase() {
		return listaDeEntidadesBase;
	}
	public void setListaDeEntidadesBase(List<Base> listaDeEntidadesBase) {
		this.listaDeEntidadesBase = listaDeEntidadesBase;
	}
	public TipoJuridica getTipoEntidadJuridica() {
		return tipoEntidadJuridica;
	}
	public void setTipoEntidadJuridica(TipoJuridica tipoEntidadJuridica) {
		this.tipoEntidadJuridica = tipoEntidadJuridica;
	}
}
