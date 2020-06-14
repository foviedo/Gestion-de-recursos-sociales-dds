package domain;

import java.util.List;

public class Usuario {
	
    private String usuario;
    private byte[] password;
    private List<Egreso> bandejaDeEntrada;
    

    public Usuario(String usuario, String password) {
        GeneradorPassword generadorPassword = new GeneradorPassword();
        byte[] hash = generadorPassword.encriptarPassword(usuario, password);
        this.usuario = usuario;
        this.password = hash;
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
