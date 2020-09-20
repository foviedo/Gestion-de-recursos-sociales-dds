package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class ClaseQueVoyABorrar {

	@Id
	@GeneratedValue
	private long Id;
	
	String foo;
	int bar;
	
}
