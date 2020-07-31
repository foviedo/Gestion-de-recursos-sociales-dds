package domain;

import java.util.TimerTask;

public class TareaValidarEgresos extends TimerTask {
	@Override
	public void run() {
		RepositorioEgresos.egresosSinValidar().forEach(Egreso::validarme);
		System.out.println("xd esto anda");
	}
}
