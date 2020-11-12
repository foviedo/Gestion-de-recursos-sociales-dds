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
		Spark.get("/egreso", ControllerHome::showEgreso, transformer);
		Spark.post("/egreso", ControllerHome::postEgreso, transformer);
		Spark.get("/egresos", ControllerHome::showEgresos, transformer);
		Spark.get("/egresos/:id/item", ControllerHome::agregarItemAlEgreso,transformer);
		Spark.get("/entidades", ControllerHome::verEntidades, transformer);
		Spark.get("/entidades-juridicas", ControllerHome::verJuridicas, transformer);
		Spark.get("/entidades-bases", ControllerHome::verBases, transformer);
		Spark.put("/entidades-bases/:id_entidad", ControllerHome::cambiarCategoriaDeEntidad,transformer); 
		Spark.put("/entidades-juridicas/:id_entidad", ControllerHome::cambiarCategoriaDeEntidad,transformer);

		/*Spark.before((req, res) -> {
			if (req.pathInfo().equals("/login")) {
				return;
			}

			if (SessionService.getSessionId(req) == null) {
				res.redirect("/login");
			}
		});*/
	}
}
