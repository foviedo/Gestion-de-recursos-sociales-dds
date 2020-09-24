package domain;

import javax.persistence.*;

@Entity
public class Proveedor {
	
	@Id
	@GeneratedValue
	private long id;
	
	String nombreProveedor;
	int identificador;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_direccionPostal")
	DireccionPostal direccionPostal;
	
	Proveedor(String nombreProveedor, int identificador, DireccionPostal direccionPostal){
		this.nombreProveedor = nombreProveedor;
		this.identificador = identificador;
		this.direccionPostal = direccionPostal;
	}
}
