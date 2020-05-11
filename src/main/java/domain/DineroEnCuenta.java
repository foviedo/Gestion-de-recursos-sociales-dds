package domain;

public class DineroEnCuenta implements MedioDePago{
	int cuenta;
	public DineroEnCuenta (int n) {
		this.cuenta = n;
	}
	public boolean prueba() {
		return true;
	}
}
