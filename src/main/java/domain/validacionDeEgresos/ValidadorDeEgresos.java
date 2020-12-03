package domain.validacionDeEgresos;

import java.util.*;

import javax.persistence.EntityManager;

import domain.Egreso;
import domain.EstadoEgreso;
import domain.RepositorioEgresos;

public class ValidadorDeEgresos {

	List<Validacion> validaciones = new ArrayList<Validacion>();

	
	public ValidadorDeEgresos(Validacion criterioEgreso) {
		Validacion validador1 = Validacion.VALIDAR_CANTIDAD_PRESUPUESTOS;
		Validacion validador2 = Validacion.VALIDAR_COMPRA_EN_BASE_A_PRESUPUESTO;
		validaciones.add(validador1);
		validaciones.add(validador2);
		validaciones.add(criterioEgreso);
	}

	/*void validarEgresoPendientes() {

		egresosPorValidar.forEach((unEgreso) -> {
			this.validar(unEgreso);
		});
		egresosPorValidar.clear();

	}*/

	public void validar(Egreso unEgreso, EntityManager unEntity) {
		System.out.println("estoy en validador de egresos");
		validaciones.forEach(action -> 	System.out.println("Tengo un validador"));
		if (validaciones.stream().allMatch(validacion -> validacion.esValido(unEgreso))) {
			unEgreso.setEstadoValidacion(EstadoEgreso.VALIDO);
			RepositorioEgresos.getInstance().agregarEgreso(unEgreso, unEntity);

		} else {

			unEgreso.setEstadoValidacion(EstadoEgreso.INVALIDO);
			RepositorioEgresos.getInstance().agregarEgreso(unEgreso, unEntity);
		}

		unEgreso.enviarResultadoACadaUsuario();

	}

}
