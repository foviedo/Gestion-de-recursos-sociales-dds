package domain;

import exception.EgresosMaximosException;

public class MontoEgresosSuperado implements Funcionalidad {
	public void valida(Entidad unaEntidad, Operaciones miOperacion) {
		if(miOperacion == Operaciones.AGREGAR_EGRESO && unaEntidad.tengoLaCantidadMaximaDeEgresos()) {
			throw new EgresosMaximosException("tenes la cantidad maxima de egresos");
		}
	}
}
