package domain;

import javax.persistence.*;

@Entity
public class Documento extends PersistentEntity {

	
	String tipo;
	int numeroDocumento;
	
	public Documento(String tipo, int numero) {
		this.tipo = tipo;
		this.numeroDocumento = numero;
	}
	public Documento() {
		
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getNumeroDocumento() {
		return numeroDocumento;
	}
}
