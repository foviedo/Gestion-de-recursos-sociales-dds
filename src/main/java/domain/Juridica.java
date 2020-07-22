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

	public Juridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscripcion) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscripcion = codInscripcion;
	}
	

	void agregarEntidadBase(Base unaEntidad){
		if (categoria != null) {
			categoria.agregarEntidadBase(this);

		}
		unaEntidad.serAgregada();
		listaDeEntidadesBase.add(unaEntidad);
	}
	
}
