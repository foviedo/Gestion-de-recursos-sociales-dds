package domain;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class RepositorioEntidadJuridica extends RepositorioEntidad {
    private static RepositorioEntidadJuridica instancia;

    public static RepositorioEntidadJuridica getInstance() {
        if (instancia == null) {
            instancia = new RepositorioEntidadJuridica();
        }
        return instancia;
    }

    public List<Juridica> getJuridicas() {
        return entityManager().createQuery("from Juridica").getResultList();
    }

	public Juridica getJuridica(long juridicaId) {
		return entityManager().find(Juridica.class, juridicaId);
	}
}
