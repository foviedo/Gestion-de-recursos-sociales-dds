package domain;

public class DineroEnCuenta implements MedioDePago {
	String cuenta;

	public DineroEnCuenta(String n) {
		this.cuenta = n;
	}

	public boolean prueba() {
		return true;
	}
}
