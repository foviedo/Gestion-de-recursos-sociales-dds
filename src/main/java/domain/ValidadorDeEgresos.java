package domain;

import java.util.*;
import java.util.stream.Collectors;

public class ValidadorDeEgresos {

	List<Validacion> validaciones = new ArrayList<Validacion>();

	
	public ValidadorDeEgresos(Validacion criterioEgreso) {
		Validacion validador1 = new ValidarCantidadPresupuestos();
		Validacion validador2 = new ValidarCompraEnBaseAPresupuesto();
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
