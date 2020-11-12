package domain;
import javax.persistence.*;
@Entity
@PrimaryKeyJoinColumn(name = "id_entidad_madre" )
public class Base extends Entidad {
	String nombreFicticio;
	String descripcion;
	@ManyToOne
	Juridica juridicaAsociada;
	
	Base(String nombreFicticio, String descripcion) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
	}
	public void serAgregada() {
		if(categoria != null) {
			categoria.serAgregadoAUnaJuridica(this);
		}
	}
	
	public Base(){
		
	}

	public Juridica getJuridicaAsociada() {
		return juridicaAsociada;
	}
	public void setJuridicaAsociada(Juridica juridicaAsociada) {
		this.juridicaAsociada = juridicaAsociada;
	}

	public String getNombreFicticio() {
		return nombreFicticio;
	}
	public void setNombreFicticio(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
