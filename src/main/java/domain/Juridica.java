package domain;

import java.util.ArrayList;
import java.util.List;

public class Juridica extends Entidad {
	Categoria categoria;
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscripcion;
	List<Base> listaDeEntidadesBase = new ArrayList<Base>();
	List<Egreso> egresos;
	


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
	
	void agregarEgreso(Egreso unEgreso){
		categoria.agregarEgreso(this,egreso);
	}
	void agregarEntidadBase(Entidad unaEntidad){
		categoria.agregarEntidadBase(this,unaEntidad);
	}
}
