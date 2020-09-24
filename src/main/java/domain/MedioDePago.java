package domain;

import javax.persistence.*;

@Entity
public class MedioDePago {
	
	public MedioDePago(TipoMedioDePago tipo, String string) {
		this.tipo = tipo;
		this.identificador = string;
	}
	@Id
	@GeneratedValue
	private long Id;
	
	@Enumerated(EnumType.STRING)
	TipoMedioDePago tipo;
	String identificador;

}
