package domain;

public class Juridica implements Entidad {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscripcion;

	Juridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscripcion) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscripcion = codInscripcion;
	}
}
