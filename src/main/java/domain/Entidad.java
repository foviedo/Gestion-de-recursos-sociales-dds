package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Entidad {
	//Map<String,Double> informeGastos = new HashMap<String,Double>();
	//List<ItemReporte> informeGastos;
	List<Egreso> egresos = new ArrayList<Egreso>();

	void generarReporte(){
		//egresosMios = RepositorioEgresos.todosLosEgresos.stream().filter(unEgreso -> unEgreso.destinatario == this).collect(Collectors.toList());
		
	}
	
	ItemReporte informeGastos(String etiqueta) {
		List<Egreso> egresosEtiquetados = egresos.stream().filter(unEgreso-> (unEgreso.contengoEtiqueta(etiqueta))).collect(Collectors.toList());
		return new ItemReporte(etiqueta, egresosEtiquetados);
		
	}
}
