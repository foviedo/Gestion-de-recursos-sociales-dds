package domain;

import exception.ReadFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PasswordComunValidador implements Validador {
    @Override
    public Boolean noEsValido(Usuario usuario) {
        String password = usuario.getPassword();
        Path path = Paths.get("src/main/resources/utils/10k-most-common.txt");
        Stream<String> stream;
        try {
            stream = Files.lines(path);
            return stream.anyMatch(palabraComun -> palabraComun.equals(password));
        } catch (IOException e) {
            throw new ReadFileException();
        }
    }
}
