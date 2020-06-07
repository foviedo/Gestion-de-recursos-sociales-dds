package domain;

public class Proveedor {
	String nombreProveedor;
	int identificador;
	DireccionPostal direccionPostal;
	
	Proveedor(String nombreProveedor, int identificador, DireccionPostal direccionPostal){
		this.nombreProveedor = nombreProveedor;
		this.identificador = identificador;
		this.direccionPostal = direccionPostal;
	}
}
