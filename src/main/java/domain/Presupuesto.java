package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Presupuesto extends PersistentEntity {


	String detalle;
	@OneToMany
	@JoinColumn(name="id_presupuesto")
	List<Item> items = new ArrayList<Item>();

	public Presupuesto(String detalle, List<Item> items) {
		this.detalle = detalle;
		this.items = items;
	}

	public List<Item> getListaItems() {
		return items;
	}

	public double total() {
		return items.stream().mapToDouble(unItem -> unItem.getMonto()).sum();
	}
}
