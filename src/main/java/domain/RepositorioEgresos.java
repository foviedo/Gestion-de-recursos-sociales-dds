package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioEgresos {
	static List<Egreso> todosLosEgresos = new ArrayList<Egreso>();
	
	static List<Egreso> getTodosLosEgresos(){		
		return RepositorioEgresos.todosLosEgresos;
	}
	
	static List<Egreso> egresosSinValidar(){
		return todosLosEgresos.stream().filter(Egreso::estoySinValidar).collect(Collectors.toList());
	}
	static void agregarEgreso(Egreso unEgreso) {
		todosLosEgresos.add(unEgreso);
	}

}
