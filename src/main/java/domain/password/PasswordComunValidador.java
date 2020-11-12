package domain.password;

import exception.ReadFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PasswordComunValidador implements Validador {
    @Override
    public boolean esValido(String nombreUsuario, String password) {
        Path path = Paths.get("src/main/resources/utils/10k-most-common.txt");
        Stream<String> stream;
        try {
            stream = Files.lines(path);
            return stream.noneMatch(palabraComun -> palabraComun.equals(password));
        } catch (IOException e) {
            throw new ReadFileException();
        }
    }
}
