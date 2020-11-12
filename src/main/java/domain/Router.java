package domain;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.after;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		Spark.staticFiles.location("/public");

		Spark.get("/", ControllerHome::index, transformer);		
		Spark.get("/login", ControllerHome::show, transformer);
		Spark.post("/login", ControllerHome::login, transformer);
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
		Spark.put("/entidades-bases/:id_entidad", ControllerHome::cambiarCategoriaDeEntidad,transformer); 
		Spark.put("/entidades-juridicas/:id_entidad", ControllerHome::cambiarCategoriaDeEntidad,transformer);
		ControllerHome unController = new ControllerHome();
		Spark.get("entidades-juridicas", unController::verJuridicas);
		Spark.get("entidades-bases", unController::verBases);
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
