package domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;


public class RepositorioEgresos {
	static final RepositorioEgresos INSTANCE = new RepositorioEgresos();

	List<Egreso> getTodosLosEgresos(EntityManager unEntity){
		return unEntity.createQuery("from Egreso",Egreso.class).getResultList();
	}
	
	public List<Egreso> egresosSinValidar(EntityManager unEntity){
		EntityTransaction transaction = unEntity.getTransaction();
		transaction.begin();
		List<Egreso> losEgresos = unEntity.createQuery("from Egreso where estadoValidacion = 'SIN_VALIDAR'",Egreso.class).getResultList();
		transaction.commit();
		losEgresos.forEach(unEgreso -> unEgreso.instanciaTuValidador());
		return losEgresos;
	}
	public void agregarEgreso(Egreso unEgreso, EntityManager unEntity) {
		unEntity.persist(unEgreso);
	}
	
	private RepositorioEgresos() {
		
	}
	
	public static RepositorioEgresos getInstance() {
		return INSTANCE;
	}

}
