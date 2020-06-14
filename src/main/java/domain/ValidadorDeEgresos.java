package domain;

import java.util.*;
import java.util.stream.Collectors;

public class ValidadorDeEgresos {

	List<Egreso> egresosPorValidar = new ArrayList<Egreso>();
	List<Validacion> validaciones = new ArrayList<Validacion>();

	private static final ValidadorDeEgresos instance = new ValidadorDeEgresos();

	public static ValidadorDeEgresos getInstance() {
		return instance;
	}

	void validarEgresoPendientes() {

		egresosPorValidar.forEach((unEgreso) -> {
			this.validar(unEgreso);
		});

	}

	void validar(Egreso unEgreso) {

		if (validaciones.stream().allMatch(validacion -> validacion.validar(unEgreso))) {

			unEgreso.setEstadoValidacion(EstadoEgreso.VALIDO);

		} else {

			unEgreso.setEstadoValidacion(EstadoEgreso.INVALIDO);
		}

		egresosPorValidar.remove(unEgreso);
		unEgreso.enviarResultadoACadaUsuario();

	}

	List<Egreso> verMisResultados(Usuario unUsuario) {
		List<Egreso> misEgresos = RepositorioEgresos.getTodosLosEgresos().stream()
				.filter(unEgreso -> unEgreso.esDe(unUsuario)).collect(Collectors.toList());
		return misEgresos;

	}

}
