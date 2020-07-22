package domain;

import java.util.ArrayList;
import java.util.List;

public class Juridica extends Entidad {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscripcion;
	List<Base> listaDeEntidadesBase = new ArrayList<Base>();

	public Juridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscripcion,List<Base> listaDeEntidades) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscripcion = codInscripcion;
		this.listaDeEntidadesBase = listaDeEntidades;
	}
	

	void agregarEntidadBase(Base unaEntidad){
		categoria.agregarEntidadBase(this,unaEntidad);
		listaDeEntidadesBase.add(unaEntidad);
	}
}
