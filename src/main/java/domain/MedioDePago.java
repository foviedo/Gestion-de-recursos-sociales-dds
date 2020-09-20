package domain;

import javax.persistence.*;

@Entity
public class MedioDePago {
	
	@Id
	@GeneratedValue
	private long Id;
	
	@Enumerated(EnumType.STRING)
	TipoMedioDePago tipo;
	String identificador;

}
