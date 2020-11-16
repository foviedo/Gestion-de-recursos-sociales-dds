package domain;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEntidadJuridica extends AbstractPersistenceTest implements WithGlobalEntityManager {
    private static RepositorioEntidadJuridica instancia;

    public static RepositorioEntidadJuridica getInstance() {
        if (instancia == null) {
            instancia = new RepositorioEntidadJuridica();
        }
        return instancia;
    }

    public void init() {
        EntityManager em = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaccion = em.getTransaction();
        Juridica juridica1 = new Juridica ("Arcos Dorados","McDonaritsu",3,"avenida",5);
        juridica1.setTipoEntidadJuridica(TipoJuridica.TRAMO1);
        Categoria cat1 = new Categoria(null,"Restaurante");
        Categoria cat2 = new Categoria(null,"RTS");
        Juridica juridica2 = new Juridica("El sillon de escarcha","Warcraft III",10,"murloc",4);
        juridica2.setTipoEntidadJuridica(TipoJuridica.PEQUENIA);
        transaccion.begin();
        em.persist(cat1);
        em.persist(cat2);
        juridica1.setCategoria(cat1);
        juridica2.setCategoria(cat2);
        em.persist(juridica1);
        em.persist(juridica2);
        transaccion.commit();
    }

    public void modificarCategoria(Long entidadJuridicaId, Long categoriaId) {
        Juridica juridica = entityManager().find(Juridica.class, entidadJuridicaId);
        Categoria categoria = entityManager().find(Categoria.class, categoriaId);
        juridica.setCategoria(categoria);

        withTransaction(() -> {
            entityManager().persist(juridica);
        });
    }

    public List<Juridica> getJuridicas() {
        return entityManager().createQuery("from Juridica").getResultList();
    }
}
