package domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class RepositorioOrganizaciones extends AbstractPersistenceTest implements WithGlobalEntityManager{
		   private static RepositorioOrganizaciones instancia;
		   public static RepositorioOrganizaciones getInstance() {
		        if (instancia == null) {
		            instancia = new RepositorioOrganizaciones();
		        }
		        return instancia;
		    }
		
		    public void init() {
		    Organizacion organizacion1 = new Organizacion();
		    Organizacion organizacion2 = new Organizacion();
		    Categoria categoria1 = new Categoria();
		    Categoria categoria2 = new Categoria();
		    Categoria categoria3 = new Categoria();
		    Base base1 = new Base();
		    Base base2 = new Base();
		    Juridica juridica1 = new Juridica();
		    Juridica juridica2 = new Juridica();
		    
		    categoria1.setNombre("Restaurante");
		    categoria2.setNombre("RTS");
		    categoria3.setNombre("Educación");
		    
		    EntityTransaction crearCategorias = entityManager().getTransaction();
		    
		    crearCategorias.begin();
		    entityManager().persist(categoria1);
		    entityManager().persist(categoria2);
		    entityManager().persist(categoria3);
		    crearCategorias.commit();
		    
		    base1.setNombreFicticio("Age of Empires II Definitive Edition");
		    base1.setDescripcion("Adiccion de Gaston");
		    base1.setCategoria(categoria2);
		    
		    base2.setNombreFicticio("Banana");
		    base2.setDescripcion("Microondas");
		    base2.setCategoria(categoria3);
		    
		    EntityTransaction crearBases = entityManager().getTransaction();
		    
		    crearBases.begin();
		    entityManager().persist(base1);
		    entityManager().persist(base2);
		    crearBases.commit();
		    
		    juridica1.setCategoria(categoria1);
		    juridica1.setCodInscripcion(5);
		    juridica1.setCuit(3);
		    juridica1.setDireccionPostal("avenida");
		    juridica1.setRazonSocial("Arcos Dorados");
		    juridica1.setTipoEntidadJuridica(TipoJuridica.TRAMO1);
		    juridica1.setNombreFicticio("McDonaritsu");
		    List<Base> basesJuridica1 = new ArrayList<>();
		    basesJuridica1.add(base1);
		    juridica1.setListaDeEntidadesBase(basesJuridica1);
		    
		    juridica2.setCategoria(categoria2);
		    juridica2.setRazonSocial("El sillon de Escarcha");
		    juridica2.setCodInscripcion(4);
		    juridica2.setCuit(10);
		    juridica2.setDireccionPostal("murloc");
		    juridica2.setTipoEntidadJuridica(TipoJuridica.PEQUENIA);
		    juridica2.setNombreFicticio("Warcraft III");
		    List<Base> basesJuridica2 = new ArrayList<>();
		    basesJuridica2.add(base2);
		    juridica2.setListaDeEntidadesBase(basesJuridica2);
		    
		    EntityTransaction crearJuridicas = entityManager().getTransaction();
		    
		    crearJuridicas.begin();
		    persist(juridica1);
		    persist(juridica2);
		    crearJuridicas.commit();
		    
		    List<Entidad> entidadesOrganizacion1 = new ArrayList<>();
		    List<Entidad> entidadesOrganizacion2 = new ArrayList<>();
		    entidadesOrganizacion1.add(juridica1);
		    entidadesOrganizacion1.add(juridica2);
		    entidadesOrganizacion1.add(base1);
		    entidadesOrganizacion2.add(base2);
		    organizacion1.setEntidades(entidadesOrganizacion1);
		    organizacion2.setEntidades(entidadesOrganizacion2);
		    
		    EntityTransaction crearOrganizaciones = entityManager().getTransaction();
		    
		    crearOrganizaciones.begin();
		    persist(organizacion1);
		    persist(organizacion2);
		    crearOrganizaciones.commit();
		    }

			public long getOrganizacion(Long entidadId) {
				Query query = entityManager().createNativeQuery("SELECT id_organizacion FROM entidad WHERE id = :idOrg");
				query.setParameter("idOrg", entidadId);
				BigInteger id = (BigInteger) query.getSingleResult();
				return id.longValue();
			}
	}	
