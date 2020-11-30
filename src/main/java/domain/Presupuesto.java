package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Presupuesto extends PersistentEntity {


	String detalle;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_presupuesto")
	List<Item> items = new ArrayList<Item>();

	public Presupuesto(String detalle, List<Item> items) {
		this.detalle = detalle;
		this.items = items;
	}
	public Presupuesto() {
		
	}

	public List<Item> getListaItems() {
		return items;
	}
	
	void agregarItem(Item unItem) {
		items.add(unItem);
	}
	
	public String getDetalle() {
		return detalle;
	}

	public double total() {
		return items.stream().mapToDouble(unItem -> unItem.getMonto()).sum();
	}
}
