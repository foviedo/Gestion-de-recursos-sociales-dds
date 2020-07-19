package domain;

public class ValidarCantidadPresupuestos implements Validacion {
	
	public boolean esValido(Egreso unEgreso) {
		return unEgreso.tenesLosPresupuestosSuficientes();
	}

}
