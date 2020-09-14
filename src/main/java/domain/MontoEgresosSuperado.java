package domain;


public class MontoEgresosSuperado extends Funcionalidad {
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
