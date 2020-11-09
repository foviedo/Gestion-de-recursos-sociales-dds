package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import domain.password.GeneradorPassword;
@Entity
public class Usuario extends PersistentEntity {
	
	
    private String usuario;
    private String passwordShippuden;
    private byte[] password;
    @Transient
    public List<Egreso> bandejaDeEntrada;
    

    public Usuario(String usuario, String password) {
        GeneradorPassword generadorPassword = new GeneradorPassword();
        byte[] hash = generadorPassword.encriptarPassword(usuario, password);
        this.passwordShippuden = password;
        this.usuario = usuario;
        this.password = hash;
        this.bandejaDeEntrada = new ArrayList<Egreso>();
    }
    public Usuario() {
    	
    };

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public byte[] getPassword() {
        return password;
    }
    
    public String getPassword2() {
    	return passwordShippuden;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
    
    public void setPassword2(String password2) {
        this.passwordShippuden = password2;
    }
    
    void agrerarResultado(Egreso unEgreso) {
    	bandejaDeEntrada.add(unEgreso);
    }

}
