package domain.validacionDeEgresos;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import domain.RepositorioEgresos;

public class TareaValidarEgresos {
	public static void main(String[] args) {
        EntityManager unEntity = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaccion = unEntity.getTransaction();
        transaccion.begin();
		RepositorioEgresos.getInstance().egresosSinValidar(unEntity).forEach(unEgreso -> unEgreso.validarme(unEntity));
        PerThreadEntityManagers.closeEntityManager();
        transaccion.commit();
		System.out.println("Validando egresos");
	}
}
