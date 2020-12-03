package domain.validacionDeEgresos;

import java.util.TimerTask;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.Egreso;
import domain.RepositorioEgresos;

public class TareaValidarEgresos extends TimerTask {
	@Override
	public void run() {
        EntityManager unEntity = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaccion = unEntity.getTransaction();
        transaccion.begin();
		RepositorioEgresos.getInstance().egresosSinValidar(unEntity).forEach(unEgreso -> unEgreso.validarme(unEntity));
        PerThreadEntityManagers.closeEntityManager();
        transaccion.commit();
		System.out.println("Validando egresos");
	}
}
