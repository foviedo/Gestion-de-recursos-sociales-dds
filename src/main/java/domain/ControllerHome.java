package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import domain.validacionDeEgresos.Validacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.Optional;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.TemplateEngine;

public class ControllerHome implements WithGlobalEntityManager {
	public static ModelAndView index(Request req, Response res) {
		
/*		Usuario user = UsuarioRepositorio.get().findAny();

		String apodo = req.queryParams("apodo");
		List<Captura> capturas = 
				Optional.fromNullable(apodo)
				.transform(it -> user.findByApodo(it))
				.or(user.getCapturas());
		*/
		HashMap<String, Object> viewModel = new HashMap<>();
		//viewModel.put("apodo", apodo);
		//viewModel.put("capturas", capturas);
		
		return new ModelAndView(
				viewModel, 
				"home.hbs");
	}
	
	public static ModelAndView show(Request req,Response res) {
		return new ModelAndView(
				null, 
				"login.hbs");
	}
	
	public static ModelAndView login (Request req, Response res) {
		String user = req.queryParams("usuario");
		String password = req.queryParams("password");
		res.cookie("usuario_login", user);
		try {
			Optional<Usuario> usuario = RepositorioUsuarios.getInstance().getUsuario(user);
			if (usuario.isPresent() && usuario.get().validarLogin(user, password)) {
				req.session(true);
				SessionService.setSessionId(req, usuario.get().getId());
				res.redirect("/");
			} else {
				res.redirect("/login");
			}

			return null;
		} catch(Exception exception) {
			res.redirect("/login");
		}

		return null;
	}
	
	public static ModelAndView home(Request req, Response res){
		return new ModelAndView(null, "home.hbs");
	}
	
	public static ModelAndView showEgreso(Request req,Response res) {
		return new ModelAndView(
				null, 
				"cargar-egreso.hbs");
	}
	
	public static ModelAndView postEgreso(Request req,Response res) {
		Documento unDocumento = new Documento(req.queryParams("tipo_documento"),Integer.parseInt(req.queryParams("numero_documento")));
		MedioDePago unMedio = new MedioDePago(TipoMedioDePago.valueOf(req.queryParams("tipoMedioPago")), req.queryParams("identificadorMedioPago"));
		DireccionPostal unaDireccion = new DireccionPostal(req.queryParams("calleProveedor"),req.queryParams("alturaProveedor"),req.queryParams("pisoProveedor"),req.queryParams("departamentoProveedor"),req.queryParams("cpProveedor"),req.queryParams("paisProveedor"),req.queryParams("provinciaProveedor"),req.queryParams("ciudadProveedor"));
		Proveedor unProveedor = new Proveedor(req.queryParams("nombreProveedor"),Integer.parseInt(req.queryParams("identificadorProveedor")),unaDireccion);
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(req.queryParams("fechaOperacion"), dateformatter);
		Egreso unEgreso = new Egreso(unDocumento,unMedio,unProveedor,ld.atStartOfDay(),new ArrayList<Item>(),new ArrayList<Usuario>(), Validacion.valueOf(req.queryParams("criterioValidacion")),new ArrayList<String>());
		RepositorioEgresos.getInstance().agregarEgreso(unEgreso);
		//res.redirect("/idEgreso");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",unEgreso.getId());
		res.redirect("/");
		return null;
	//TODO: hacer validaciones varias
		}
	

	
	public static ModelAndView showEgresos(Request req,Response res) {
		List<Egreso> todosLosEgresos = RepositorioEgresos.getInstance().getTodosLosEgresos();
		HashMap<String, Object> elMap = new HashMap<>();
		elMap.put("todosLosEgresos", todosLosEgresos);
		return new ModelAndView(elMap, "mostrar-egresos.hbs");
	}
	
	public static ModelAndView agregarItemAlEgreso(Request req,Response res) {
		String id = req.params("id");
		HashMap<String,Object> elMap = new HashMap<>();
		elMap.put("id", id);
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Egreso unEgreso = em.find(Egreso.class, Long.parseLong(id));
		elMap.put("items", unEgreso.getListaDeItems());
		return new ModelAndView(elMap, "cargar-item.hbs");
	}
	
	public static ModelAndView postAgregarItemAlEgreso(Request req,Response res) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Moneda moneda =em.find(Moneda.class, req.queryParams("id_moneda"));
		if(moneda == null) {
			moneda = new Moneda(req.queryParams("id_moneda"),req.queryParams("descripcion_moneda"),req.queryParams("simbolo_moneda"));
		}
		Item item = new Item(req.queryParams("descripcion_item"),moneda,Double.parseDouble(req.queryParams("costo_item")));
		Egreso unEgreso = em.find(Egreso.class, Long.parseLong(req.params("id")));
		unEgreso.agregarItem(item);
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		em.persist(unEgreso);
		transaccion.commit();
		res.redirect("/");
		return null;
	}
	
	public static ModelAndView verPresupuestos(Request req, Response res) {
		String id = req.params("id");
		HashMap<String,Object> elMap = new HashMap<>();
		elMap.put("id", id);
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Egreso unEgreso = em.find(Egreso.class, Long.parseLong(id));
		elMap.put("presupuestos", unEgreso.getPresupuestos());
		return new ModelAndView(elMap, "ver-presupuestos.hbs");
	}
	
	public static ModelAndView cargarPresupuesto(Request req,Response res) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Presupuesto unPresupuesto = new Presupuesto(req.queryParams("detalle_presupuesto"), new ArrayList<Item>());
		Egreso unEgreso = em.find(Egreso.class, Long.parseLong(req.params("id")));
		unEgreso.agregarPresupuesto(unPresupuesto);
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		em.persist(unPresupuesto);
		em.persist(unEgreso);
		transaccion.commit();
		res.redirect("/");
		return null;
	}
	
	public static ModelAndView agregarItemAlPresupuesto(Request req,Response res) {
		String id = req.params("id");
		HashMap<String,Object> elMap = new HashMap<>();
		elMap.put("id", id);
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Presupuesto unPresupuesto = em.find(Presupuesto.class, Long.parseLong(id));
		elMap.put("items", unPresupuesto.getListaItems());
		return new ModelAndView(elMap, "cargar-item-presupuesto.hbs");
	}
	
	public static ModelAndView postAgregarItemAlPresupuesto(Request req,Response res) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Moneda moneda =em.find(Moneda.class, req.queryParams("id_moneda"));
		if(moneda == null) {
			moneda = new Moneda(req.queryParams("id_moneda"),req.queryParams("descripcion_moneda"),req.queryParams("simbolo_moneda"));
		}
		Item item = new Item(req.queryParams("descripcion_item"),moneda,Double.parseDouble(req.queryParams("costo_item")));
		Presupuesto unPresupuesto = em.find(Presupuesto.class, Long.parseLong(req.params("id")));
		unPresupuesto.agregarItem(item);
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		em.persist(item);
		em.persist(unPresupuesto);
		transaccion.commit();
		res.redirect("/");
		return null;
	}
	
	public static ModelAndView verEntidades(Request req, Response res) {
		return new ModelAndView(null, "ver-entidades.hbs");
	}
	
	public String verJuridicas(Request req, Response res) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		Juridica juridica1 = new Juridica ("hola","halo",3,"pinia",5);
		juridica1.setTipoEntidadJuridica(TipoJuridica.TRAMO1);
		Categoria cat1 = new Categoria(null,"buen dia");
		Categoria cat2 = new Categoria(null,"aprobame el parcial xfa");
		Juridica juridica2 = new Juridica("elpepe","juju",10,"murloc",4);
		juridica2.setTipoEntidadJuridica(TipoJuridica.PEQUENIA);
		transaccion.begin();
		em.persist(cat1);
		em.persist(cat2);
		juridica1.setCategoria(cat1);
		juridica2.setCategoria(cat2);
		em.persist(juridica1);
		em.persist(juridica2);
		transaccion.commit();
		String filtro = req.queryParams("nombre_categoria");
		TypedQuery<Juridica> queryJuridicasFiltradas;
		if(filtro == null || filtro == ""){
			queryJuridicasFiltradas = em.createQuery("FROM Juridica j" ,Juridica.class);
		}else
		{
		queryJuridicasFiltradas = em.createQuery("FROM Juridica j WHERE j.categoria.nombre LIKE :nombre_categoria" ,Juridica.class);
		queryJuridicasFiltradas.setParameter("nombre_categoria",filtro);
		}
		List<Juridica> listaTodoJuridicas = queryJuridicasFiltradas.getResultList();
		HashMap<String, Object> juridicas = new HashMap<>();
		juridicas.put("juridicas", listaTodoJuridicas);
		ModelAndView modelo = new ModelAndView(juridicas,"mostrar-juridicas.hbs");
		return new HandlebarsTemplateEngine().render(modelo);
	}
	
	public String verBases(Request req, Response res){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		Base base1 = new Base("ycomoescell","que pasara cuando te absorba a ti");
		Base base2 = new Base("entidadBaseGenerica88","descripcionGenerica89");
		Categoria cat3 = new Categoria(null,"monotributista");
		Categoria cat4 = new Categoria(null, "multitributista");
		base1.setCategoria(cat3);
		base2.setCategoria(cat4);
		transaccion.begin();
		em.persist(cat3);
		em.persist(cat4);
		em.persist(base1);
		em.persist(base2);
		transaccion.commit();
		String filtro = req.queryParams("nombre_categoria");
		TypedQuery<Base> queryBasesFiltradas;
		if(filtro == null || filtro == ""){
			queryBasesFiltradas = em.createQuery("FROM Base b" ,Base.class);
		}else{
		queryBasesFiltradas = em.createQuery("FROM Base b WHERE b.categoria.nombre LIKE :nombre_categoria" ,Base.class);
		queryBasesFiltradas.setParameter("nombre_categoria",filtro);
		}
		List<Base> listaTodoJuridicas = queryBasesFiltradas.getResultList();
		HashMap<String, Object> bases = new HashMap<>();
		bases.put("bases", listaTodoJuridicas);
		ModelAndView modelo = new ModelAndView(bases,"mostrar-bases.hbs");
		return new HandlebarsTemplateEngine().render(modelo);
	}
	
	public static ModelAndView cambiarCategoriaDeEntidad(Request req, Response res) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return null;
	}
}

