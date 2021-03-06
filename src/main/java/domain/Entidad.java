package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TipoDeEntidad")
public abstract class Entidad extends PersistentEntity {

	
	@OneToMany
	@JoinColumn(name = "id_Entidad")
	protected List<Egreso> egresos = new ArrayList<>();
	@ManyToOne
	protected Categoria categoria;
	String nombreFicticio;
	
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
	
	public String getNombreFicticio() {
		return nombreFicticio;
	}
	public void setNombreFicticio(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}
	
	public HashMap<String,Object> agregarmeAHashmap(HashMap<String,Object> mapa, long idOrganizacion){
		mapa.put("nombreFicticio",this.getNombreFicticio());
		mapa.put("categoria", this.getCategoria());
		mapa.put("idOrg", idOrganizacion);
		mapa.put("id", this.getId());
		return mapa;
	}
}
