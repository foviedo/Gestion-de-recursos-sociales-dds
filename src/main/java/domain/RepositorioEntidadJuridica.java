package domain;



import java.util.List;

import javax.persistence.EntityManager;

public class RepositorioEntidadJuridica extends RepositorioEntidad {
    private static RepositorioEntidadJuridica instancia;

    public static RepositorioEntidadJuridica getInstance() {
        if (instancia == null) {
            instancia = new RepositorioEntidadJuridica();
        }
        return instancia;
    }

    public List<Juridica> getJuridicas(EntityManager unEntity) {
        return unEntity.createQuery("from Juridica").getResultList();
    }

	public Juridica getJuridica(long juridicaId, EntityManager unEntity) {
		return unEntity.find(Juridica.class, juridicaId);
	}
}
