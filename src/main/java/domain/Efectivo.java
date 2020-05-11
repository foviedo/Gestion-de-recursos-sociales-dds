package domain;

public class Efectivo implements MedioDePago{
	int identificador;
	public Efectivo (int n) {
		this.identificador = n;
	}
	public boolean prueba() {
		return true;
	}
}
