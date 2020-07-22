package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Entidad {
	protected List<Egreso> egresos = new ArrayList<>();
	protected Categoria categoria;
	protected int cantMaxDeEgresos;
	
	public Reporte generarReporte(String etiqueta) {
		List<Egreso> egresosEtiquetados = egresos.stream().filter(unEgreso-> unEgreso.contieneEtiqueta(etiqueta)).collect(Collectors.toList());
		return new Reporte(etiqueta, egresosEtiquetados);
	}

	public void agregarEgreso(Egreso unEgreso) {
		if (categoria != null) {
			categoria.agregarEgreso(this);
		}
		egresos.add(unEgreso);
	}

	public boolean tengoLaCantidadMaximaDeEgresos() {
		return egresos.size() == cantMaxDeEgresos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setCantidadMaxEgresos(int unaCantidad) {
		cantMaxDeEgresos = unaCantidad;
	}
}
