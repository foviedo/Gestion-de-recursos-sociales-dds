package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Juridica extends Entidad {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscripcion;
	List<Base> listaDeEntidadesBase = new ArrayList<Base>();
	


	Juridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscripcion,List<Base> listaDeEntidades
			,Categoria categoria) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscripcion = codInscripcion;
		this.listaDeEntidadesBase = listaDeEntidades;
		this.categoria= categoria;
	}
	

	void agregarEntidadBase(Base unaEntidad){
		categoria.agregarEntidadBase(this,unaEntidad);
		listaDeEntidadesBase.add(unaEntidad);
	}
}
