package domain;

import java.util.List;
import javax.persistence.*;

@Entity
public class Organizacion extends PersistentEntity {

	@OneToMany
	@JoinColumn(name = "id_organizacion")
	List<Entidad> entidades;
}
