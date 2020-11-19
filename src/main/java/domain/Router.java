package domain;

import static spark.Spark.after;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		Spark.staticFiles.location("/public");
		//RepositorioOrganizaciones.getInstance().init();
		Spark.get("/", ControllerHome::index, transformer);		
		Spark.get("/login", ControllerHome::show, transformer);
		Spark.post("/login", ControllerHome::login, transformer);
		Spark.get("/logout", ControllerHome::logout, transformer);
		Spark.get("/registro", ControllerHome::registro, transformer);
		Spark.post("/registro", ControllerHome::postRegistro, transformer);
		Spark.get("/egreso", ControllerHome::showEgreso, transformer);
		Spark.post("/egreso", ControllerHome::postEgreso, transformer);
		Spark.get("/egresos", ControllerHome::showEgresos, transformer);
		Spark.get("/egresos/:id/item", ControllerHome::agregarItemAlEgreso,transformer);
		Spark.post("/egresos/:id/item", ControllerHome::postAgregarItemAlEgreso,transformer);
		Spark.get("/egresos/:id/presupuesto", ControllerHome::verPresupuestos,transformer);
		Spark.post("/egresos/:id/presupuesto", ControllerHome::cargarPresupuesto,transformer);
		Spark.get("/egresos/:id/etiquetas", ControllerHome::verEtiquetas,transformer);
		Spark.post("/egresos/:id/etiquetas", ControllerHome::cargarEtiqueta,transformer);
		Spark.get("/presupuesto/:id/item", ControllerHome::agregarItemAlPresupuesto,transformer);
		Spark.post("/presupuesto/:id/item", ControllerHome::postAgregarItemAlPresupuesto,transformer);
		Spark.get("/entidades", ControllerHome::verEntidades, transformer);
		Spark.get("/entidades-juridicas/:id_entidad", ControllerHome::verUnaJuridica, transformer);
		Spark.get("/entidades-bases/:id_entidad", ControllerHome::verUnaBase,transformer);
		Spark.post("/entidades-juridicas/:entidadId/categoria/:categoriaId", ControllerHome::cambiarCategoriaDeEntidad,transformer);
		Spark.post("/entidades-bases/:entidadId/categoria/:categoriaId", ControllerHome::cambiarCategoriaDeEntidad,transformer);
		after((request, response) -> {
            PerThreadEntityManagers.getEntityManager();
            PerThreadEntityManagers.closeEntityManager();
        });
		Spark.before((req, res) -> {
			if (req.pathInfo().equals("/login")) {
				return;
			}
			if (SessionService.getSessionId(req) == null) {
				res.redirect("/login");
			}
		});
	}
}
