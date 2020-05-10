package domain;

import java.util.List;

public class Egreso {
	Documento documentoComercial;
	MedioDePago medioDePago;
	Proveedor proveedor;
	String fechaDeOperacion;
	int valorTotal;
	//Datos propios
	List<Item> listaDeItems;
	
	Egreso(Documento documentoComercial, MedioDePago medioDePago, Proveedor proveedor, String fechaDeOperacion, int valorTotal, List<Item> listaDeItems){
		this.documentoComercial = documentoComercial;
		this.medioDePago = medioDePago;
		this.proveedor = proveedor;
		this.fechaDeOperacion = fechaDeOperacion;
		this.valorTotal = valorTotal;
		this.listaDeItems = listaDeItems;
	}
}
