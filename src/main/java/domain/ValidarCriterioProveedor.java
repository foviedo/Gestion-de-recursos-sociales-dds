package domain;

public class ValidarCriterioProveedor implements Validacion {

	public boolean validar(Egreso unEgreso) {
		double minimoPresupuesto = unEgreso.getPresupuestos().stream()
				.mapToDouble(unPresupuesto -> unPresupuesto.total()).min().getAsDouble();
		return minimoPresupuesto == unEgreso.getMontoTotal();
		
	}

}
