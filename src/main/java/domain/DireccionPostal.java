package domain;

import javax.persistence.*;

@Entity
public class DireccionPostal {
	
	@Id
	@GeneratedValue
	private long Id;
	private String calle;
	private String altura;
	private String piso;
	private String departamento;
	private String codigoPostal;
	private String pais;
	private String provincia;
	private String ciudad;

	public DireccionPostal(String calle,
						   String altura,
						   String piso,
						   String departamento,
						   String codigoPostal,
						   String pais,
						   String provincia,
						   String ciudad) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
		this.provincia = provincia;
		this.ciudad= ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public String getPais() {
		return pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public String getAltura() {
		return altura;
	}

	public String getPiso() {
		return piso;
	}

	public String getDepartamento() {
		return departamento;
	}
}
