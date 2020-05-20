package domain;

import exception.GeneratorPasswordException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class GeneradorHashing {
    private static final String PBKDF2 = "PBKDF2WithHmacSHA1";
    private static final Integer NUMERO_DE_ITERACIONES = 655536;
    private static final Integer NUMERO_DE_BITS_SALT = 256;
    private static final Integer NUMERO_DE_BITS_CLAVE_GENERADA = 128;

    public byte[] encriptarPassword(String password) {
        try {
            byte[] salt = generarSalt();
            SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, NUMERO_DE_ITERACIONES, NUMERO_DE_BITS_CLAVE_GENERADA);
            return factory.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new GeneratorPasswordException("Error al intentar hashear el password", e);
        }
    }

    private byte[] generarSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] bytes = new byte[NUMERO_DE_BITS_SALT];
        sr.nextBytes(bytes);
        return bytes;
    }
}
