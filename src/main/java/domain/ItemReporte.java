package domain;

import java.util.List;

public class ItemReporte {
	String etiqueta;
	List<Egreso> egresosEtiquetados;
	ItemReporte(String etiqueta, List<Egreso> egresosEtiquetados) {
		this.etiqueta = etiqueta;
		this.egresosEtiquetados = egresosEtiquetados;
	}
	
	double getMontoTotalEtiqueta() {
		return egresosEtiquetados.stream().mapToDouble(unEgreso -> unEgreso.getMontoTotal()).sum();
	}
}
