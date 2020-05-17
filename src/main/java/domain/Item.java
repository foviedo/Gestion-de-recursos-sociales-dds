package domain;

public class Item {
	String descripcion;
	double valor;

	public Item(String descrip, int n) {
		this.valor = n;
		this.descripcion = descrip;
	}

	public double getValor() {
		return valor;
	}
}
