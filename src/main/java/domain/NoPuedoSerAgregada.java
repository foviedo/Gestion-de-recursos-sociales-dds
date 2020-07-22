package domain;

import exception.NoPuedoSerAgregadoException;
public class NoPuedoSerAgregada implements Funcionalidad {
	public void valida(Entidad unaEntidad, Operaciones miOperacion) {
		if(miOperacion == Operaciones.ENTIDAD_SER_AGREGADO) {
			throw new NoPuedoSerAgregadoException("esta entidad base no permite ser agregada a una entidad juridica");
		}
	}
}
