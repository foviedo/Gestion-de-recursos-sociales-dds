package domain;

import domain.ControllerHome;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

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
		Spark.get("entidad", ControllerHome::elegirEntidadForm, transformer);
		Spark.get("entidad-juridica", ControllerHome::showFormJuridica, transformer);
		Spark.get("entidad-base", ControllerHome::showFormBase, transformer);
		Spark.post("entidad-juridica", ControllerHome::fillFormJuridica,transformer);
		Spark.post("entidad-base", ControllerHome::fillFormBase, transformer);
	}
}
