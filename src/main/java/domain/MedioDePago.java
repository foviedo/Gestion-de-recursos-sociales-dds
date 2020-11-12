package domain;

import javax.persistence.*;

@Entity
public class MedioDePago extends PersistentEntity {
	
	public MedioDePago(TipoMedioDePago tipo, String string) {
		this.tipo = tipo;
		this.identificador = string;
	}
	public MedioDePago() {
		
	}
	
	
	@Enumerated(EnumType.STRING)
	TipoMedioDePago tipo;
	String identificador;
	
	public TipoMedioDePago getTipo() {
		return tipo;
	}
	
	public String getIdentificador() {
		return identificador;
	}

}
