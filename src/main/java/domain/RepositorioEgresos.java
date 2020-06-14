package domain;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEgresos {
	
	List<Egreso> todosLosEgresos = new ArrayList<Egreso>();
	
	private static final RepositorioEgresos instance = new RepositorioEgresos();

	public static RepositorioEgresos getInstance() {
		return instance;
	}
	
	
	static List<Egreso> getTodosLosEgresos(){
		
		return RepositorioEgresos.getInstance().todosLosEgresos;
	}

}
