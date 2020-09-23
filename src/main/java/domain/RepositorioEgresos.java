package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Id;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;


public class RepositorioEgresos implements WithGlobalEntityManager {
	static final RepositorioEgresos INSTANCE = new RepositorioEgresos();
	List<Egreso> todosLosEgresos = new ArrayList<Egreso>();
	List<Egreso> getTodosLosEgresos(){
		return entityManager().createQuery("from Egreso",Egreso.class).getResultList();
	}
	
	List<Egreso> egresosSinValidar(){
		return entityManager().createQuery("from Egreso where estadoValidacion = 'SIN_VALIDAR'",Egreso.class).getResultList();
		//return todosLosEgresos.stream().filter(Egreso::estoySinValidar).collect(Collectors.toList());
	}
	void agregarEgreso(Egreso unEgreso) {
		entityManager().persist(unEgreso);
		//todosLosEgresos.add(unEgreso);
	}
	
	private RepositorioEgresos() {
		
	}
	
	public static RepositorioEgresos getInstance() {
		return INSTANCE;
	}

}
