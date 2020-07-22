package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Entidad {
	List<Egreso> egresos = new ArrayList<Egreso>();
	Categoria categoria;
	int cantMaxDeEgresos;
	
	ItemReporte informeGastos(String etiqueta) {
		List<Egreso> egresosEtiquetados = egresos.stream().filter(unEgreso-> (unEgreso.contengoEtiqueta(etiqueta))).collect(Collectors.toList());
		return new ItemReporte(etiqueta, egresosEtiquetados);
		
	}
	void agregarEgreso(Egreso unEgreso){
		categoria.agregarEgreso(this);
		egresos.add(unEgreso);
	}
	boolean tengoLaCantidadMaximaDeEgresos() {
		return egresos.size() == cantMaxDeEgresos;
	}
}
