package domain;

import java.util.List;
import javax.persistence.*;

@Entity
public class Organizacion {
	@Id
	@GeneratedValue
	long id;
	@OneToMany
	@JoinColumn(name = "id_organizacion")
	List<Entidad> entidades;
}
