package domain;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	List<Funcionalidad> funcionalidades = new ArrayList<>();
	
	void agregarEgreso(Entidad miEntidad) {
		funcionalidades.forEach(unaFuncionalidad -> unaFuncionalidad.valida(miEntidad,Operaciones.AGREGAR_EGRESO));
	}
	void agregarEntidadBase(Entidad miEntidad, Entidad entidadAAgregar) {
		funcionalidades.forEach(unaFuncionalidad -> unaFuncionalidad.valida(miEntidad,Operaciones.AGREGAR_ENTIDAD));
		entidadAAgregar.getCategoria().funcionalidades.forEach(unaFuncionalidad -> unaFuncionalidad.valida(entidadAAgregar, Operaciones.ENTIDAD_SER_AGREGADO));
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
}
