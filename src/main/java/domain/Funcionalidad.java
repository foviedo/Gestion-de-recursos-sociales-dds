package domain;

import exception.FuncionalidadException;

public abstract class Funcionalidad {
	
	public void valida(Entidad unaEntidad, Operaciones miOperacion) {
		
	}
	
	void validacionAbstraida(Operaciones miOperacion, Operaciones operacionAComparar, String texto){
		if(miOperacion == operacionAComparar) {
			throw new FuncionalidadException(texto);
		}
	}
}

