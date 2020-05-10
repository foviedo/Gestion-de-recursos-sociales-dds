package domain;

public class TarjetaDeDebito implements MedioDePago{
	int numero;
	public TarjetaDeDebito (int n) {
		this.numero = n;
	}
	
	public boolean prueba() {
		return true;
	}
}
