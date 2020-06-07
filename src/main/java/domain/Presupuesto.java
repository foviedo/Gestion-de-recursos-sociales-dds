package domain;

import java.util.ArrayList;
import java.util.List;

import exception.EgresosException;

public class Presupuesto {

	String detalle;
	List<Egreso> listaDeEgresos = new ArrayList<Egreso>();
	
	public Presupuesto(List<Egreso> listaDeEgresos) { // Obligatoriamente se instancia un presupuesto si tiene previamente egresos
		if(listaDeEgresos.isEmpty()) {
			throw new EgresosException();
		}
		this.listaDeEgresos = listaDeEgresos;
	}

	public double total() {

		return listaDeEgresos.stream().mapToDouble(egreso -> egreso.valorTotal()).sum();
	}

}
