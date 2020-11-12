package domain;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import java.util.List;

public class RepositorioCategorias extends AbstractPersistenceTest implements WithGlobalEntityManager {
    private static RepositorioCategorias instancia;

    public static RepositorioCategorias getInstance() {
        if (instancia == null) {
            instancia = new RepositorioCategorias();
        }
        return instancia;
    }

    public List<Categoria> getCategorias() {
        return entityManager()
                .createQuery("from Categoria")
                .getResultList();
    }

}
