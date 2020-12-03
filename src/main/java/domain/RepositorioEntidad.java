package domain;

import java.math.BigInteger;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class RepositorioEntidad extends AbstractPersistenceTest implements WithGlobalEntityManager{
	public void modificarCategoria(Long entidadId, Optional<String> categoriaNombre, EntityManager unEntity) {
		Entidad entidad = unEntity.find(Entidad.class, entidadId);
		if (categoriaNombre != null) {
			Query query = unEntity.createNativeQuery("SELECT id FROM categoria WHERE nombre = :categoriaNombre");
			query.setParameter("categoriaNombre", categoriaNombre.get());
			BigInteger id; 
			try {
				id = (BigInteger) query.getSingleResult();
			}
				catch (NoResultException noExisteLaCategoria){
				id = null;
			}
			Categoria categoria;
			if (id != null) {
				categoria = unEntity.find(Categoria.class, id.longValue());
				entidad.setCategoria(categoria);
			} else {
				Categoria categoriaNueva = new Categoria();
				categoriaNueva.setNombre(categoriaNombre.get());;
				unEntity.persist(categoriaNueva);
					
				Query queryNuevo = unEntity.createNativeQuery("SELECT id FROM categoria WHERE nombre = :nombreCategoria");
				queryNuevo.setParameter("nombreCategoria", categoriaNombre.get());
				id = (BigInteger) queryNuevo.getSingleResult();
			}
			Categoria categoriaInsertada = unEntity.find(Categoria.class, id.longValue());
			entidad.setCategoria(categoriaInsertada);
		}
		else {
			entidad.setCategoria(null);
		}
		unEntity.persist(entidad);
	}



}
