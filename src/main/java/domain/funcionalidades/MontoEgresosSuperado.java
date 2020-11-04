package domain.funcionalidades;
import javax.persistence.*;

import domain.Entidad;
import domain.Operaciones;

@Entity
public class MontoEgresosSuperado extends Funcionalidad {
	
	@Column(name = "limiteEgresos")
	int limiteDeEgresos;
	
	public MontoEgresosSuperado(int cantidad) {
		limiteDeEgresos = cantidad;
	}
	
	public void valida(Entidad unaEntidad, Operaciones miOperacion) {
		if(unaEntidad.tengoLaCantidadMaximaDeEgresos(limiteDeEgresos)) {
			this.validacionAbstraida(miOperacion, Operaciones.AGREGAR_EGRESO, "tenes la cantidad maxima de egresos");
		}
	}
}
