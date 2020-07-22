package domain;

import java.util.List;

public class Reporte {
	private String etiqueta;
	private List<Egreso> egresos;

	public Reporte(String etiqueta, List<Egreso> egresos) {
		this.etiqueta = etiqueta;
		this.egresos = egresos;
	}
	
	public double getMontoTotal() {
		return egresos.stream().mapToDouble(Egreso::getMontoTotal).sum();
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public List<Egreso> getEgresos() {
		return egresos;
	}
}
