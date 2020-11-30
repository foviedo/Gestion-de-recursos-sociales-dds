package domain;

import domain.validacionDeEgresos.ValidadorDeEgresosEjecutable;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		
		ValidadorDeEgresosEjecutable.getInstance().validarTareasCadaTiempoEnMilisegundos();	

		DebugScreen.enableDebugScreen();
		Spark.port(getHerokuAssignedPort());
		Router.configure();

	}
	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 9000; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
