package domain;

import java.util.ArrayList;
import java.util.List;

public class Juridica implements Entidad {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscripcion;
	List<Entidad> listaDeEntidadesBase = new ArrayList<Entidad>();
	


	Juridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscripcion,List<Entidad> listaDeEntidades) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscripcion = codInscripcion;
		this.listaDeEntidadesBase = listaDeEntidades;
	}
}
