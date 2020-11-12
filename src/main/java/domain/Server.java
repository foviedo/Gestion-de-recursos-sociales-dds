package domain;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) throws Exception {
		Bootstrap.init();
		DebugScreen.enableDebugScreen();
		Spark.port(9000);
		Router.configure();
		Usuario admin = new Usuario();
		admin.setUsuario("admin");
		admin.setPassword("123");
		RepositorioUsuarios.getInstance().guardarUsuario(admin);
	}
}
