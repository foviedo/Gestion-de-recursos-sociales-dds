package domain;
import javax.persistence.*;
@Entity
@PrimaryKeyJoinColumn(name = "id_entidad_madre" )
public class Base extends Entidad {
	String nombreFicticio;
	String descripcion;

	Base(String nombreFicticio, String descripcion) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
	}
	public void serAgregada() {
		if(categoria != null) {
			categoria.serAgregadoAUnaJuridica(this);
		}
	}
}
