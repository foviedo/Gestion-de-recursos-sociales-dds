package domain.funcionalidades;

import exception.FuncionalidadException;
import javax.persistence.*;

import domain.Entidad;
import domain.Operaciones;
import domain.PersistentEntity;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoFuncionalidad")
public abstract class Funcionalidad extends PersistentEntity {

	
	public void valida(Entidad unaEntidad, Operaciones miOperacion) {
		
	}
	
	void validacionAbstraida(Operaciones miOperacion, Operaciones operacionAComparar, String texto){
		if(miOperacion == operacionAComparar) {
			throw new FuncionalidadException(texto);
		}
	}
}

