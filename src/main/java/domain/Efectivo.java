package domain;

public class Efectivo implements MedioDePago {
	String identificador;

	public Efectivo(String n) {
		this.identificador = n;
	}

	public boolean prueba() {
		return true;
	}
}
