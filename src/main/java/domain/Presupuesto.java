package domain;

import java.util.ArrayList;
import java.util.List;

import exception.EgresosException;

public class Presupuesto {

	String detalle;
	List<Item> items = new ArrayList<Item>();

	public Presupuesto(String detalle, List<Item> items) {
		this.detalle = detalle;
		this.items = items;
	}

	List<Item> getListaItems() {
		return items;
	}

	public double total() {

		return items.stream().mapToDouble(unItem -> unItem.getMonto()).sum();
	}

}
