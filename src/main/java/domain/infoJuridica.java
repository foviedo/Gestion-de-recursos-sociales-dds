package domain;

public class infoJuridica {

	String razon_social;
	int cuit;
	String nombre_ficticio;
	String tipo_entidad_juridica;
	String categoria;
	int id_entidad;
	
public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public int getCuit() {
		return cuit;
	}
	public void setCuit(int cuit) {
		this.cuit = cuit;
	}
	public String getNombre_ficticio() {
		return nombre_ficticio;
	}
	public void setNombre_ficticio(String nombre_ficticio) {
		this.nombre_ficticio = nombre_ficticio;
	}
	public String getTipo_entidad_juridica() {
		return tipo_entidad_juridica;
	}
	public void setTipo_entidad_juridica(String tipo_entidad_juridica) {
		this.tipo_entidad_juridica = tipo_entidad_juridica;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getId_entidad() {
		return id_entidad;
	}
	public void setId_entidad(int id_entidad) {
		this.id_entidad = id_entidad;
	}
}
