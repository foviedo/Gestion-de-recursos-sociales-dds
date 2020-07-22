package domain;
import exception.NoAgregarEntidadBaseException;

public class NoPuedoAgregarEntidadesBase implements Funcionalidad{
	public void valida(Entidad unaEntidad, Operaciones miOperacion) {
		if(miOperacion == Operaciones.AGREGAR_ENTIDAD) {
			throw new NoAgregarEntidadBaseException("no puedo agregar entidades base nuevas");
		}
	}
}
