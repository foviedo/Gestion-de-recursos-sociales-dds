package domain;

public class TarjetaDeCredito implements MedioDePago {
	String numero;

	public TarjetaDeCredito(String n) {
		this.numero = n;
	}

	public boolean prueba() {
		return true;
	}
}
