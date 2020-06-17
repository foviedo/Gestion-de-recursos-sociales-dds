package domain;

public class ValidarCantidadPresupuestos implements Validacion {
	
	public boolean validar(Egreso unEgreso) {
		return unEgreso.tenesLosPresupuestosSuficientes();
	}

}
