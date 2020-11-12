package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import domain.funcionalidades.Funcionalidad;

@Entity
public class Categoria extends PersistentEntity {

	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_categoria")
	List<Funcionalidad> funcionalidades = new ArrayList<>();
	String nombre;
	
	public Categoria() {}
	public Categoria(List<Funcionalidad> listFunc, String nombre) {
		this.nombre = nombre;
		this.funcionalidades = listFunc;
	}
	
	void agregarEgreso(Entidad miEntidad) {
		funcionalidades.forEach(unaFuncionalidad -> unaFuncionalidad.valida(miEntidad,Operaciones.AGREGAR_EGRESO));
	}
	void agregarEntidadBase(Entidad miEntidad) {
		funcionalidades.forEach(unaFuncionalidad -> unaFuncionalidad.valida(miEntidad,Operaciones.AGREGAR_ENTIDAD));
	}
	
	void serAgregadoAUnaJuridica(Entidad entidadAAgregar) {
		if(entidadAAgregar.getCategoria() != null) {
			entidadAAgregar.getCategoria().funcionalidades.forEach(unaFuncionalidad -> unaFuncionalidad.valida(entidadAAgregar, Operaciones.ENTIDAD_SER_AGREGADO));
		}
	}
	
	void agregarFuncionalidad(Funcionalidad unaFuncionalidad) {
		funcionalidades.add(unaFuncionalidad);
	}
	void quitarFuncionalidad(Funcionalidad unaFuncionalidad) {
		funcionalidades.remove(unaFuncionalidad);
	}
	void limpiarFuncionalidades() {
		funcionalidades.clear();
	}
	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}
	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

