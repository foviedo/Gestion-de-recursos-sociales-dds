package domain.funcionalidades;

import javax.persistence.Entity;

import domain.Entidad;
import domain.Operaciones;

@Entity
public class NoPuedoAgregarEntidadesBase extends Funcionalidad{
	public void valida(Entidad unaEntidad, Operaciones miOperacion) {
		this.validacionAbstraida(miOperacion, Operaciones.AGREGAR_ENTIDAD, "no puedo agregar entidades base nuevas");
	}
}
