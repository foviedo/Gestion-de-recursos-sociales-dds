package domain;

public class TarjetaDeCredito implements MedioDePago {
	int numero;
	public TarjetaDeCredito (int n) {
		this.numero = n;
	}
	public boolean prueba() {
		return true;
	}
}
