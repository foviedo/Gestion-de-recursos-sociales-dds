package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
public class Documento {
	@Id
	@GeneratedValue
	private long id;
	
	String tipo;
	int numeroDocumento;
	
	public Documento(String tipo, int numero) {
		this.tipo = tipo;
		this.numeroDocumento = numero;
	}
}
