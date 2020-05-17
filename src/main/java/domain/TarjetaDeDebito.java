package domain;

public class TarjetaDeDebito implements MedioDePago{
	String numero;
	public TarjetaDeDebito (String n) {
		this.numero = n;
	}
	
	public boolean prueba() {
		return true;
	}
}
