package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String descripcion;
	@ManyToOne(cascade=CascadeType.ALL)
	private Moneda moneda;
	private double monto;

	public Item(String descripcion, Moneda moneda, double monto) {
		this.descripcion = descripcion;
		this.moneda = moneda;
		this.monto = monto;
	}

	public double getMonto() {
		return monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Moneda getMoneda() {
		return moneda;
	}
}
