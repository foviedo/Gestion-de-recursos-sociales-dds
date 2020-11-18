package domain;

import java.nio.charset.Charset;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import domain.validacionDeEgresos.Validacion;
import exception.PasswordInvalidoException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.stream.Collectors;
import spark.template.handlebars.HandlebarsTemplateEngine;

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

	public static ModelAndView logout(Request req, Response res){
		SessionService.removeSessionId(req);
		res.redirect("/login");
		return null;
	}

	public static ModelAndView registro(Request req, Response res){
		return new ModelAndView(null, "registro.hbs");
	}
	
	public static ModelAndView postRegistro (Request req, Response res) {
		List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

		try {
			Optional<Usuario> usuario = RepositorioUsuarios.getInstance().getUsuario(pairs.get(0).getValue());
			if (usuario.isPresent() ) {
				Map<String, Object> model = new HashMap<>();
				model.put("message", "El usuario que intenta agregar ya existe, pruebe de nuevo con un usuario distinto");
				model.put("icon", "&#10060");
				return new ModelAndView(model, "mensaje.hbs");
			} else {
				Usuario nuevoUsuario = new Usuario(pairs.get(0).getValue(), pairs.get(1).getValue());
				RepositorioUsuarios.getInstance().guardarUsuario(nuevoUsuario);
				Map<String, Object> model = new HashMap<>();
				model.put("message", "Usted se ha registrado correctamente");
				model.put("icon", "&#9989");
				return new ModelAndView(model, "mensaje.hbs");
			}
		} catch(PasswordInvalidoException e) {
			Map<String, Object> model = new HashMap<>();
			model.put("message", "La contraseña que acaba de ingresar no es segura, pruebe ingresando otra");
			model.put("icon", "&#10060");
			return new ModelAndView(model, "mensaje.hbs");
		} catch (Exception exception) {
			res.status(500);
			return new ModelAndView(null, "");
		}
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
		//TODO: pasar por body esta informacion, no por query params. Lucas consulta por otra manera de hacerlo
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
	} //TODO: hacer un logout
	//TODO: ponerle boostrap a la tabla de ver egresos
	//TODO: hacer andar el css de los que tienen .../:id/...
	//TODO: que el registro pueda accederse pq me redirige a login
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
	
	public static ModelAndView verEtiquetas(Request req, Response res) {
		String id = req.params("id");
		HashMap<String,Object> elMap = new HashMap<>();
		elMap.put("id", id);
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Egreso unEgreso = em.find(Egreso.class, Long.parseLong(id));
		elMap.put("etiquetas", unEgreso.getEtiquetas());
		return new ModelAndView(elMap, "ver-etiquetas.hbs");
	}
	
	public static ModelAndView cargarEtiqueta(Request req,Response res) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Egreso unEgreso = em.find(Egreso.class, Long.parseLong(req.params("id")));
		unEgreso.agregarEtiqueta(req.queryParams("etiqueta"));
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
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
		String categoria = req.queryParams("categoria");
		List<Juridica> juridicas = RepositorioEntidadJuridica.getInstance().getJuridicas();
		List<Base> bases = RepositorioEntidadBase.getInstance().getBase();
		if(categoria != null) {
			if(!categoria.isEmpty()){	
				juridicas = juridicas.stream().filter((x) -> x.getCategoria().getNombre().equals(categoria)).collect(Collectors.toList());
				bases = bases.stream().filter((x)-> x.getCategoria().getNombre().equals(categoria)).collect(Collectors.toList());
				System.out.println("TODO MAL");
			}
		}
		HashMap<String, Object> entidades = new HashMap<>();
		entidades.put("juridicas",juridicas);
		entidades.put("bases", bases);
		return new ModelAndView(entidades, "ver-entidades.hbs");
	}
	
	public String verJuridicas(Request req, Response res) {
		List<Juridica> listaJuridicas = RepositorioEntidadJuridica.getInstance().getJuridicas();
		HashMap<String, Object> juridicas = new HashMap<>();
		juridicas.put("juridicas", listaJuridicas);
		juridicas.put("categorias", RepositorioCategorias.getInstance().getCategorias());
		ModelAndView modelo = new ModelAndView(juridicas,"mostrar-juridicas.hbs");
		return new HandlebarsTemplateEngine().render(modelo);
	}
	
	public static ModelAndView verUnaJuridica(Request req, Response res) {
		Long entidadJuridicaId = Long.parseLong(req.params(":id_entidad"));
		Optional<String> existeEntidad = null;
		Juridica entidadAMostrar = RepositorioEntidadJuridica.getInstance().getJuridica(entidadJuridicaId);
		if(entidadAMostrar == null) {
			if(RepositorioEntidadBase.getInstance().getBase(entidadJuridicaId) != null) {
				res.redirect("/entidades-bases/"+RepositorioEntidadBase.getInstance().getBase(entidadJuridicaId).getId().toString());
			}
		}else{
			existeEntidad = Optional.of("Existe la entidad juridica");
		}
		HashMap<String, Object> juridica = new HashMap<>();
		long organizacion = RepositorioOrganizaciones.getInstance().getOrganizacion(entidadJuridicaId);
		juridica.put("nombreFicticio",entidadAMostrar.getNombreFicticio());
		juridica.put("razonSocial", entidadAMostrar.getRazonSocial());
		juridica.put("cuit", entidadAMostrar.getCuit());
		juridica.put("codInscripcion", entidadAMostrar.getCodInscripcion());
		juridica.put("existeEntidad", existeEntidad.isPresent());
		juridica.put("idOrg", organizacion);
		juridica.put("tipoEntidadJuridica", entidadAMostrar.getTipoEntidadJuridica());
		juridica.put("categoria", entidadAMostrar.getCategoria());
		return new ModelAndView(juridica,"ver-juridica.hbs");
	}
	
	public static ModelAndView verUnaBase(Request req, Response res) {
		Long entidadBaseId = Long.parseLong(req.params(":id_entidad"));
		Optional<String> existeEntidad = null;
		Base entidadAMostrar = RepositorioEntidadBase.getInstance().getBase(entidadBaseId);
		if(entidadAMostrar == null) {
			if(RepositorioEntidadJuridica.getInstance().getJuridica(entidadBaseId) != null) {
				res.redirect("/entidades-juridicas/"+RepositorioEntidadJuridica.getInstance().getJuridica(entidadBaseId).getId().toString());
			}
		}else {
			existeEntidad = Optional.of("Existe la entidad base");
		}
		HashMap<String, Object> base = new HashMap<>();
		long organizacion = RepositorioOrganizaciones.getInstance().getOrganizacion(entidadBaseId);
		long juridicaId = RepositorioEntidadBase.getInstance().getJuridica(entidadBaseId);
		Juridica juridicaAsociada = RepositorioEntidadJuridica.getInstance().getJuridica(juridicaId);
		base.put("categoria",entidadAMostrar.getCategoria());
		base.put("descripcion",entidadAMostrar.getDescripcion());
		base.put("nombreFicticio",entidadAMostrar.getNombreFicticio());
		base.put("nombreJuridica", juridicaAsociada.getNombreFicticio());
		base.put("existeEntidad", existeEntidad.isPresent());
		base.put("idOrg",organizacion);
		base.put("idJuridica", juridicaAsociada.getId());
		return new ModelAndView(base,"ver-base.hbs");
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
		Long entidadJuridicaId = Long.parseLong(req.params(":entidadJuridicaId"));
		Long categoriaId = Long.parseLong(req.params(":categoriaId"));
		RepositorioEntidadJuridica.getInstance().modificarCategoria(entidadJuridicaId, categoriaId);
		HashMap<String, Object> viewModel = new HashMap<>();
		return new ModelAndView(viewModel,"home.hbs");
	}
}

