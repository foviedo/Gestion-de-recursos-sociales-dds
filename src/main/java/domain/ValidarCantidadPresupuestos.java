package domain;

public class ValidarCantidadPresupuestos implements Validacion {
	
	public boolean validar(Egreso unEgreso) {
		return Egreso.cantidadPresupuestos == unEgreso.presupuestos.size();
	}

}
