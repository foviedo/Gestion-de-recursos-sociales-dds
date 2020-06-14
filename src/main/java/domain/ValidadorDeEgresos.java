package domain;

import java.util.*;

public class ValidadorDeEgresos {
	
	List<Egreso> egresosPorValidar = new ArrayList<Egreso>();
	List<Egreso> egresosValidados = new ArrayList<Egreso>();
	List<Validacion> validaciones = new ArrayList<Validacion>();
	
	
	private static final ValidadorDeEgresos instance = new ValidadorDeEgresos();
	
	public static ValidadorDeEgresos getInstance() {
		return instance;
	}
	
	void validarEgresoPendientes() {
		
		egresosPorValidar.forEach((unEgreso)->{this.validar(unEgreso);});
		
	}
	
	void validar(Egreso unEgreso) {
		
		validaciones.stream().allMatch(validacion->validacion.validar(unEgreso));
	}

}
