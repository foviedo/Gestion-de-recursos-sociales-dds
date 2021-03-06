package domain;

import domain.password.ValidadorPassword;
import exception.PasswordInvalidoException;
import org.apache.commons.lang3.StringUtils;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import java.util.Optional;

import javax.persistence.EntityManager;

public class RepositorioUsuarios extends AbstractPersistenceTest implements WithGlobalEntityManager {
    private static RepositorioUsuarios instancia;

    public static RepositorioUsuarios getInstance() {
        if (instancia == null) {
            instancia = new RepositorioUsuarios();
        }
        return instancia;
    }

    public void guardarUsuario(Usuario usuario, EntityManager unEntity) {
        ValidadorPassword validadorPassword = new ValidadorPassword();
        if (StringUtils.isNotEmpty(usuario.getUsuario()) && StringUtils.isNotEmpty(usuario.getPassword()) && validadorPassword.esValido(usuario.getUsuario(), usuario.getPassword())) {
            String passwordEncriptado = AESEncryptionDecryption.encrypt(usuario.getPassword());
            usuario.setPassword(passwordEncriptado);

                unEntity.persist(usuario);
        } else {
            throw new PasswordInvalidoException();
        }
    }

    public Optional<Usuario> getUsuario(String user, EntityManager unEntity) {
        return unEntity
                .createQuery("from Usuario where usuario = :user", Usuario.class)
                .setParameter("user", user).getResultList()
                .stream().findFirst();
    }
}
