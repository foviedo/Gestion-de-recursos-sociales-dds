package domain;

public class Proveedor {
	String nombreProveedor;
	int identificador;
	String direccionPostal;
	
	Proveedor(String nombreProveedor, int identificador, String direccionPostal){
		this.nombreProveedor = nombreProveedor;
		this.identificador = identificador;
		this.direccionPostal = direccionPostal;
	}
}
