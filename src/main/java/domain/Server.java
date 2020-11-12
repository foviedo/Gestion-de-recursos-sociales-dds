package domain;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) throws Exception {
		Bootstrap.init();
		DebugScreen.enableDebugScreen();
		Spark.port(9000);
		Router.configure();
	}
}
