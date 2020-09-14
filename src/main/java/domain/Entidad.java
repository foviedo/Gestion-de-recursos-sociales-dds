package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Entidad {
	protected List<Egreso> egresos = new ArrayList<>();
	protected Categoria categoria;
	
	public Reporte generarReporte(String etiqueta) {
		List<Egreso> egresosEtiquetados = egresos.stream().filter(unEgreso-> unEgreso.contieneEtiqueta(etiqueta)).collect(Collectors.toList());
		return new Reporte(etiqueta, egresosEtiquetados);
	}
	
	public List<Reporte> generarReporteGeneral(){
		List<String> etiquetasFiltradas = egresos.stream().map(egreso -> egreso.getEtiquetas()).flatMap(egreso -> egreso.stream()).distinct().collect(Collectors.toList());
		
		List<Reporte> reportesARetornar = new ArrayList<Reporte>();
		etiquetasFiltradas.forEach(unaEtiqueta -> {reportesARetornar.add(generarReporte(unaEtiqueta));});
		return reportesARetornar;
	}
	

	public void agregarEgreso(Egreso unEgreso) {
		if (categoria != null) {
			categoria.agregarEgreso(this);
		}
		egresos.add(unEgreso);
	}

	public boolean tengoLaCantidadMaximaDeEgresos(int limite) {
		return egresos.size() >= limite;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
