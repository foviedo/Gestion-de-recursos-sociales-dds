package domain;

import java.util.TimerTask;

public class TareaAEjecutarse extends TimerTask {
	@Override
	public void run() {
		RepositorioEgresos.todosLosEgresos.forEach((unEgreso) -> {unEgreso.validarme();});
		System.out.println("xd esto anda");
	}
}
