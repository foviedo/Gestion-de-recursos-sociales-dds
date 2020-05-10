package domain;

public class Base implements Entidad {
	String nombreFicticio;
	String descripcion;
	Entidad entidadJuridica;

	Base(String nombreFicticio, String descripcion, Entidad entidadJuridica) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
		this.entidadJuridica = entidadJuridica;
	}
}
