package domain;

public class Base extends Entidad {
	String nombreFicticio;
	String descripcion;
	Categoria categoria;

	Base(String nombreFicticio, String descripcion, Categoria categoria) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
		this.categoria= categoria;
	}
}
