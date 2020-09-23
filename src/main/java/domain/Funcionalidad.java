package domain;

import exception.FuncionalidadException;
import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoFuncionalidad")
public abstract class Funcionalidad {
	@Id
	@GeneratedValue
	long id;
	
	public void valida(Entidad unaEntidad, Operaciones miOperacion) {
		
	}
	
	void validacionAbstraida(Operaciones miOperacion, Operaciones operacionAComparar, String texto){
		if(miOperacion == operacionAComparar) {
			throw new FuncionalidadException(texto);
		}
	}
}

