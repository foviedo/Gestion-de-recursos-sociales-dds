package domain;

public class Base implements Entidad {
	String nombreFicticio;
	String descripcion;

	Base(String nombreFicticio, String descripcion) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
	}
}
