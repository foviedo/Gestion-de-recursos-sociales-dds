package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.google.common.base.Optional;

import domain.password.GeneradorHashing;
import domain.password.GeneradorPassword;
import domain.validacionDeEgresos.Validacion;
import exception.GeneratorPasswordException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerHome implements WithGlobalEntityManager{
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
		String nombre = req.queryParams("usuario");
		String password = req.queryParams("password");
		res.cookie("usuario_login", nombre);
		try {
			byte[] passwordEncriptado = new GeneradorPassword().encriptarPassword(nombre, password);
			EntityManager entityManager=PerThreadEntityManagers.getEntityManager();
			TypedQuery<Usuario> unQuery = entityManager.createQuery("from Usuario u WHERE u.usuario=:nombre",Usuario.class);
			unQuery.setParameter("nombre",nombre);
			List<Usuario> unaLista = unQuery.getResultList();
			if(unaLista.isEmpty()) {
				res.redirect("/login");
			}
			System.out.println(unaLista.get(0).getUsuario());
			System.out.println(password);
			System.out.println(unaLista.get(0).getPassword2());


			if(!(password.equals(unaLista.get(0).getPassword2()))) {
				res.redirect("/login");
			}
			res.redirect("/");
			return null;
		} catch(GeneratorPasswordException exception) {
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
		Egreso unEgreso = new Egreso(unDocumento,unMedio,unProveedor,ld.atStartOfDay(),new ArrayList<Item>(),new ArrayList<Usuario>(),Validacion.valueOf(req.queryParams("criterioValidacion")),new ArrayList<String>());
		RepositorioEgresos.getInstance().agregarEgreso(unEgreso);
		//res.redirect("/idEgreso");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",unEgreso.getId());
		return new ModelAndView(map, "mostrar-egreso-id.hbs");	//TODO: hacer validaciones varias
		}
	
	public static ModelAndView showEgresoId(Request req,Response res) {
		return new ModelAndView(
				null, 
				"mostrar-egreso-id.hbs");
	}
	
	public static ModelAndView cargarItem(Request req,Response res) {
		return new ModelAndView(
				null, 
				"cargar-item.hbs");
	}
	
	public static ModelAndView verEntidades(Request req, Response res) {
		return new ModelAndView(null, "ver-entidades.hbs");
	}
	
	public static ModelAndView verJuridicas(Request req, Response res) {
		return new ModelAndView(null, "mostrar-juridicas.hbs");
	}
	
	public static ModelAndView verBases(Request req, Response res) {

		EntityManager entityManager=PerThreadEntityManagers.getEntityManager();
	/*//FORMA 1 MULTIPLES CONSULTAS, BLOQUEAR LA DB
		TypedQuery<Base> queryBases = entityManager.createQuery("SELECT b.nombreFicticio, b.descripcion, c.nombre FROM Base b JOIN Entidad e ON"
				+ " (e.id = b.id_entidad_madre) JOIN Categoria c ON (c.id = e.categoria_id)", Base.class);
		List<Base> listaBases = queryBases.getResultList();
		TypedQuery<Integer> queryIDs = entityManager.createQuery("SELECT e.id FROM Base b JOIN Entidad e ON" +
		" (e.id = b.id_entidad_madre) JOIN Categoria c ON (c.id = e.categoria_id)", Integer.class);
		List<Integer> listaIds = queryIDs.getResultList();
		TypedQuery<Integer> queryIDsOrg = entityManager.createQuery("SELECT e.id_organizacion FROM Base b JOIN Entidad e ON" +
		" (e.id = b.id_entidad_madre) JOIN Categoria c ON (c.id = e.categoria_id)", Integer.class);
		List<Integer> listaIdsOrg = queryIDsOrg.getResultList();
		TypedQuery<Integer> queryIDsJurid = entityManager.createQuery("SELECT b.id_juridica FROM Base b JOIN Entidad e ON" +
		" (e.id = b.id_entidad_madre) JOIN Categoria c ON (c.id = e.categoria_id)", Integer.class);
		List<Integer> listaIdsJurid = queryIDsJurid.getResultList();
		HashMap<String, Object> base = new HashMap<>();
		base.put("bases", listaBases);
		base.put("Ids entidades", listaIds);
		base.put("Ids organizaciones", listaIdsOrg);
		base.put("Ids juridicas asociadas", listaIdsJurid);*/
	//FORMA 2 UNA SOLA CONSULTA, NUEVA CLASE
		TypedQuery<infoBase> queryTodoBases = entityManager.createQuery("SELECT b.nombreFicticio, b.descripcion,"
				+ " c.nombre, e.id, e.id_organizacion, b.id_juridica FROM Base b INNER JOIN b.id_entidad_madre as e INNER JOIN e.categoria_id as c"
				, infoBase.class);
		List<infoBase> listaTodoBases = queryTodoBases.getResultList();
		HashMap<String, Object> base2 = new HashMap<>();
		base2.put("bases", listaTodoBases);
		return new ModelAndView(base2,"mostrar-base.hbs");
	}
	public static ModelAndView cambiarCategoriaDeEntidad(Request req, Response res) {
		return null;
	}
	public static ModelAndView buscarPorCategoria(Request req, Response res) {
		String filtro = req.queryParams("nombre_categoria");
		EntityManager entityManager=PerThreadEntityManagers.getEntityManager();	
		TypedQuery<infoBase> queryBasesQueCumplen = entityManager.createQuery("SELECT b.nombreFicticio, b.descripcion,"
				+ " c.nombre, e.id, e.id_organizacion, b.id_juridica FROM Base b INNER JOIN b.id_entidad_madre as e INNER JOIN"
				+ " e.categoria_id as c with c.nombre LIKE :nombre_categoria",infoBase.class);
		queryBasesQueCumplen.setParameter("nombre_categoria",filtro);
		List<infoBase> basesQueCumplen = queryBasesQueCumplen.getResultList();
		HashMap<String, Object> bases = new HashMap<>();
		bases.put("bases", basesQueCumplen);
		return new ModelAndView(bases, "mostrar-base.hbs");
	}
}
