package domain;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

public class ControllerHome implements WithGlobalEntityManager {
	public static ModelAndView index(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
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
		System.out.println("aaaa");
		String categoria = req.queryParams("categoria");
		List<Juridica> juridicas = RepositorioEntidadJuridica.getInstance().getJuridicas();
		List<Base> bases = RepositorioEntidadBase.getInstance().getBase();
		System.out.println("aaaa");

		if(categoria != null) {
			System.out.println("aaaa1");

			if(!categoria.isEmpty()){	
				System.out.println("aaaa1");

				juridicas = juridicas.stream().filter((x) -> x.getCategoria().getNombre().equals(categoria)).collect(Collectors.toList());
				bases = bases.stream().filter((x)-> x.getCategoria().getNombre().equals(categoria)).collect(Collectors.toList());
				System.out.println("TODO MAL");
			}
		}
		System.out.println("aaaa2");

		HashMap<String, Object> entidades = new HashMap<>();
		entidades.put("juridicas",juridicas);
		entidades.put("bases", bases);
		System.out.println("aaaa2");

		return new ModelAndView(entidades, "ver-entidades.hbs");
	}
	
	public static ModelAndView verUnaJuridica(Request req, Response res) {
		Long entidadJuridicaId = Long.parseLong(req.params(":id_entidad"));
		Juridica entidadAMostrar = RepositorioEntidadJuridica.getInstance().getJuridica(entidadJuridicaId);
		if(entidadAMostrar == null) {
			if(RepositorioEntidadBase.getInstance().getBase(entidadJuridicaId) != null) {
				res.redirect("/entidades-bases/"+RepositorioEntidadBase.getInstance().getBase(entidadJuridicaId).getId().toString());
			}
			else{
				return ControllerHome.devolverErrorEntidad();
			}
		}
		HashMap<String, Object> juridica = new HashMap<>();
		long organizacion = RepositorioOrganizaciones.getInstance().getOrganizacion(entidadJuridicaId);
		juridica.put("razonSocial", entidadAMostrar.getRazonSocial());
		juridica.put("cuit", entidadAMostrar.getCuit());
		juridica.put("codInscripcion", entidadAMostrar.getCodInscripcion());
		juridica.put("tipoEntidadJuridica", entidadAMostrar.getTipoEntidadJuridica());
		juridica.put("direccionPostal", entidadAMostrar.getDireccionPostal());
		juridica = entidadAMostrar.agregarmeAHashmap(juridica, organizacion);
		return new ModelAndView(juridica,"ver-juridica.hbs");
	}
	
	public static ModelAndView verUnaBase(Request req, Response res) {
		Long entidadBaseId = Long.parseLong(req.params(":id_entidad"));
		Base entidadAMostrar = RepositorioEntidadBase.getInstance().getBase(entidadBaseId);
		if(entidadAMostrar == null) {
			if(RepositorioEntidadJuridica.getInstance().getJuridica(entidadBaseId) != null) {
				res.redirect("/entidades-juridicas/"+RepositorioEntidadJuridica.getInstance().getJuridica(entidadBaseId).getId().toString());
			}else{
				return ControllerHome.devolverErrorEntidad();
			}
		}
		HashMap<String, Object> base = new HashMap<>();
		long organizacion = RepositorioOrganizaciones.getInstance().getOrganizacion(entidadBaseId);
		long juridicaId = RepositorioEntidadBase.getInstance().getJuridica(entidadBaseId);
		Juridica juridicaAsociada = RepositorioEntidadJuridica.getInstance().getJuridica(juridicaId);
		base.put("descripcion",entidadAMostrar.getDescripcion());
		base.put("nombreJuridica", juridicaAsociada.getNombreFicticio());
		base.put("idJuridica", juridicaAsociada.getId());
		base = entidadAMostrar.agregarmeAHashmap(base, organizacion);
		return new ModelAndView(base,"ver-base.hbs");
	}
	
	private static ModelAndView devolverErrorEntidad() {
		return new ModelAndView(null,"entidad-erronea.hbs");
	}
	
	public static ModelAndView cambiarCategoriaDeEntidad(Request req, Response res) {
		Long entidadId = Long.parseLong(req.params(":entidadId"));
		Optional<String> categoriaNombre;
		categoriaNombre =  Optional.of(req.queryParams("categoria"));
		if(RepositorioEntidadBase.getInstance().getBase(entidadId) != null) {
			RepositorioEntidadBase.getInstance().modificarCategoria(entidadId, categoriaNombre);
			res.redirect("/entidades-bases/"+entidadId.toString());
		}
		if(RepositorioEntidadJuridica.getInstance().getJuridica(entidadId) != null) {
			RepositorioEntidadJuridica.getInstance().modificarCategoria(entidadId, categoriaNombre);
			res.redirect("/entidades-juridicas/"+entidadId.toString());
		}
		else {
			return ControllerHome.devolverErrorEntidad();
		}
		
		return null;
		
	}
}

