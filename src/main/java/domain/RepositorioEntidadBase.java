package domain;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class RepositorioEntidadBase extends RepositorioEntidad{
	
	   private static RepositorioEntidadBase instancia;

	    public static RepositorioEntidadBase getInstance() {
	        if (instancia == null) {
	            instancia = new RepositorioEntidadBase();
	        }
	        return instancia;
	    }
	
	    public List<Base> getBase() {
	    	return entityManager().createQuery("from Base").getResultList();
	    }

		public Base getBase(long entidadBaseId) {
			return entityManager().find(Base.class, entidadBaseId);
		}

		public long getJuridica(Long entidadBaseId) {
			Query query = entityManager().createNativeQuery("SELECT id_juridica FROM base WHERE id_entidad_madre = :idBase");
			query.setParameter("idBase", entidadBaseId);
			BigInteger id = (BigInteger) query.getSingleResult();
			return id.longValue();
		}

}