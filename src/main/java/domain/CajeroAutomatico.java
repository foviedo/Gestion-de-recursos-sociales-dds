package domain;

public class CajeroAutomatico implements MedioDePago{
	int identificador;
	public CajeroAutomatico (int n) {
		this.identificador = n;
	}
	public boolean prueba() {
		return true;
	}
}
