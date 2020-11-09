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

		
	}

}
