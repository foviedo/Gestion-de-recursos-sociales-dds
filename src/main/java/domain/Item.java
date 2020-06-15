package domain;

public class Item {
	private String descripcion;
	private Moneda moneda;
	private double monto;

	public Item(String descripcion, Moneda moneda, double monto) {
		this.descripcion = descripcion;
		this.moneda = moneda;
		this.monto = monto;
	}

	public double getMonto() {
		return monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Moneda getMoneda() {
		return moneda;
	}
}
