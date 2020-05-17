package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Egreso {
	Documento documentoComercial;
	MedioDePago medioDePago;
	Proveedor proveedor;
	LocalDate fechaDeOperacion;
	Entidad destinatario;
	
	List<Item> listaDeItems = new ArrayList<Item>();
	
	public Egreso(Documento documentoComercial, MedioDePago medioDePago, Proveedor proveedor, LocalDate fechaDeOperacion, List<Item> listaDeItems){
		this.documentoComercial = documentoComercial;
		this.medioDePago = medioDePago;
		this.proveedor = proveedor;
		this.fechaDeOperacion = fechaDeOperacion;
		this.listaDeItems = listaDeItems;
	}
	
	public boolean prueba() {
		return true;
	}
	
	public double valorTotal() {
		return listaDeItems.stream().mapToDouble(item -> item.getValor()).sum();
	}
}
