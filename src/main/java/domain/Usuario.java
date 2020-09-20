package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private long id;
	
    private String usuario;
    private byte[] password;
    @Transient
    public List<Egreso> bandejaDeEntrada;
    

    public Usuario(String usuario, String password) {
        GeneradorPassword generadorPassword = new GeneradorPassword();
        byte[] hash = generadorPassword.encriptarPassword(usuario, password);
        this.usuario = usuario;
        this.password = hash;
        this.bandejaDeEntrada = new ArrayList<Egreso>();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
    
    void agrerarResultado(Egreso unEgreso) {
    	bandejaDeEntrada.add(unEgreso);
    }

}
