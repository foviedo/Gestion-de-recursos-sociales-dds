package domain;

import java.util.*;
import java.util.stream.Collectors;

public class ValidadorDeEgresos {

	List<Egreso> egresosPorValidar = new ArrayList<Egreso>();
	List<Validacion> validaciones = new ArrayList<Validacion>();

	private static final ValidadorDeEgresos instance = new ValidadorDeEgresos();

	
	public ValidadorDeEgresos() {
		Validacion validador1 = new ValidarCantidadPresupuestos();
		Validacion validador2 = new ValidarCompraEnBaseAPresupuesto();
		Validacion validador3 = new ValidarCriterioProveedor();
		validaciones.add(validador1);
		validaciones.add(validador2);
		validaciones.add(validador3);
	}
	public static ValidadorDeEgresos getInstance() {
		return instance;
	}

	void validarEgresoPendientes() {

		egresosPorValidar.forEach((unEgreso) -> {
			this.validar(unEgreso);
		});
		egresosPorValidar.clear();

	}

	void validar(Egreso unEgreso) {

		if (validaciones.stream().allMatch(validacion -> validacion.validar(unEgreso))) {

			unEgreso.setEstadoValidacion(EstadoEgreso.VALIDO);

		} else {

			unEgreso.setEstadoValidacion(EstadoEgreso.INVALIDO);
		}

		unEgreso.enviarResultadoACadaUsuario();

	}

}
