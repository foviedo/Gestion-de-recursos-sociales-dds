package domain;

public class infoJuridica {
	String razonSocial;
	int cuit;
	String nombreFicticio;
	String direccionPostal;
	String tipoEntidadJuridica;
	int id;
	int id_organizacion;
	String categoria;

	
	public String getDireccionPostal() {
		return direccionPostal;
	}
	public void setDireccionPostal(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public int getCuit() {
		return cuit;
	}
	public void setCuit(int cuit) {
		this.cuit = cuit;
	}
	public String getNombreFicticio() {
		return nombreFicticio;
	}
	public void setNombreFicticio(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}
	public String getTipoEntidadJuridica() {
		return tipoEntidadJuridica;
	}
	public void setTipoEntidadJuridica(String tipoEntidadJuridica) {
		this.tipoEntidadJuridica = tipoEntidadJuridica;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
