package domain;

import java.util.List;
import javax.persistence.*;

@Entity
public class Organizacion extends PersistentEntity {

	@OneToMany
	@JoinColumn(name = "id_organizacion")
	List<Entidad> entidades;

	public List<Entidad> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Entidad> entidades) {
		this.entidades = entidades;
	}
	
	
	
	
}
