package domain;

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
		System.out.println(req.queryParams("tipoMedioPago"));
		return null;
	}
}
