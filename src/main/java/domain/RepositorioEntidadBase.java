package domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class RepositorioEntidadBase extends AbstractPersistenceTest implements WithGlobalEntityManager{
	
	   private static RepositorioEntidadBase instancia;

	    public static RepositorioEntidadBase getInstance() {
	        if (instancia == null) {
	            instancia = new RepositorioEntidadBase();
	        }
	        return instancia;
	    }
	
	    public void init() {
	    	EntityManager em = PerThreadEntityManagers.getEntityManager();
			EntityTransaction transaccion = em.getTransaction();
			Base base1 = new Base("Age of Empires II Definitive Edition","Adiccion de Gaston");
			Base base2 = new Base("Banana","Microondas");
			Categoria cat3 = new Categoria(null,"RTS");
			Categoria cat4 = new Categoria(null, "Educación");
			base1.setCategoria(cat3);
			base2.setCategoria(cat4);
			transaccion.begin();
			em.persist(cat3);
			em.persist(cat4);
			em.persist(base1);
			em.persist(base2);
			transaccion.commit();
	    }
	    
	    public List<Base> getBase() {
	    	return entityManager().createQuery("from Base").getResultList();
	    }
}