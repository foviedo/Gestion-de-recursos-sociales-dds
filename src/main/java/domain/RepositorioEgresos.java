package domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;


public class RepositorioEgresos implements WithGlobalEntityManager {
	static final RepositorioEgresos INSTANCE = new RepositorioEgresos();
	EntityTransaction transaction = entityManager().getTransaction();

	List<Egreso> getTodosLosEgresos(){
		return entityManager().createQuery("from Egreso",Egreso.class).getResultList();
	}
	
	public List<Egreso> egresosSinValidar(EntityManager unEntity){
		EntityTransaction transaction = unEntity.getTransaction();
		transaction.begin();
		List<Egreso> losEgresos = unEntity.createQuery("from Egreso where estadoValidacion = 'SIN_VALIDAR'",Egreso.class).getResultList();
		transaction.commit();
		losEgresos.forEach(unEgreso -> unEgreso.instanciaTuValidador());
		return losEgresos;
	}
	public void agregarEgreso(Egreso unEgreso) {
		EntityTransaction transaction = entityManager().getTransaction();
		transaction.begin();
		entityManager().persist(unEgreso);
		transaction.commit();
	}
	
	private RepositorioEgresos() {
		
	}
	
	public static RepositorioEgresos getInstance() {
		return INSTANCE;
	}

}
