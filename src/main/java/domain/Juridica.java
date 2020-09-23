package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id_entidad_madre" )
public class Juridica extends Entidad {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscripcion;
	@OneToMany
	@JoinColumn(name = "id_juridica")
	List<Base> listaDeEntidadesBase = new ArrayList<Base>();
	@Enumerated(EnumType.STRING)
	TipoJuridica tipoEntidadJuridica;

	public Juridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscripcion) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscripcion = codInscripcion;
	}
	
	void setTipoJuridica(TipoJuridica tipo) {
		this.tipoEntidadJuridica = tipo;
	}

	void agregarEntidadBase(Base unaEntidad){
		if (categoria != null) {
			categoria.agregarEntidadBase(this);

		}
		unaEntidad.serAgregada();
		listaDeEntidadesBase.add(unaEntidad);
	}
	
}
