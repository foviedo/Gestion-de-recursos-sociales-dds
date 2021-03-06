package domain;

import javax.persistence.*;

@Entity
public class Proveedor extends PersistentEntity {
	
	
	String nombreProveedor;
	int identificador;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "id_direccionPostal")
	DireccionPostal direccionPostal;
	
	Proveedor(String nombreProveedor, int identificador, DireccionPostal direccionPostal){
		this.nombreProveedor = nombreProveedor;
		this.identificador = identificador;
		this.direccionPostal = direccionPostal;
	}
	public Proveedor() {
		
	}
	
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	
	public int getIdentificador() {
		return identificador;
	}
}
