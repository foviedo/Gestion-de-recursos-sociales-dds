package domain;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	List<Funcionalidad> funcionalidades = new ArrayList<>();
	
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
}

