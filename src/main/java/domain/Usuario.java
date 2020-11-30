package domain;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Usuario extends PersistentEntity {
    private String usuario;
    private String password;
    @Transient
    public List<Egreso> bandejaDeEntrada = new ArrayList<>();

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
        this.bandejaDeEntrada = new ArrayList<>();
    }

    public Usuario() {}

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    void agregarResultado(Egreso unEgreso) {
    	bandejaDeEntrada.add(unEgreso);
    }

    public boolean validarLogin(String usuario, String password) {
        if (StringUtils.isNotEmpty(usuario) && StringUtils.isNotEmpty(password)) {
            String passwordDesencriptado = AESEncryptionDecryption.decrypt(this.password);
            return usuario.equals(this.usuario) && password.equals(passwordDesencriptado);
        } else {
            return false;
        }
    }
}
