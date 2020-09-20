package domain;

import java.util.*;
import java.util.stream.Collectors;

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

	void validar(Egreso unEgreso) {

		if (validaciones.stream().allMatch(validacion -> validacion.esValido(unEgreso))) {

			unEgreso.setEstadoValidacion(EstadoEgreso.VALIDO);

		} else {

			unEgreso.setEstadoValidacion(EstadoEgreso.INVALIDO);
		}

		unEgreso.enviarResultadoACadaUsuario();

	}

}
