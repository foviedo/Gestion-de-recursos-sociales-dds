package domain.validacionDeEgresos;

import java.util.TimerTask;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.Egreso;
import domain.RepositorioEgresos;

public class TareaValidarEgresos extends TimerTask {
	@Override
	public void run() {
        EntityManager unEntity = PerThreadEntityManagers.getEntityManager();
		RepositorioEgresos.getInstance().egresosSinValidar(unEntity).forEach(Egreso::validarme);
        PerThreadEntityManagers.closeEntityManager();

		System.out.println("Validando egresos");
	}
}
