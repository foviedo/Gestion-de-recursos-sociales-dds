package domain;

public class CajeroAutomatico implements MedioDePago{
	String identificador;
	public CajeroAutomatico (String n) {
		this.identificador = n;
	}
	public boolean prueba() {
		return true;
	}
}
