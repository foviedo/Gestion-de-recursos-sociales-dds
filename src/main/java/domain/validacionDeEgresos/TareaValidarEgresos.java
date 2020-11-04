package domain.validacionDeEgresos;

import java.util.TimerTask;

import domain.Egreso;
import domain.RepositorioEgresos;

public class TareaValidarEgresos extends TimerTask {
	@Override
	public void run() {
		RepositorioEgresos.getInstance().egresosSinValidar().forEach(Egreso::validarme);
		System.out.println("xd esto anda");
	}
}
