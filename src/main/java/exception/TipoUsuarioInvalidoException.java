package exception;

public class TipoUsuarioInvalidoException extends RuntimeException {
	
	public TipoUsuarioInvalidoException(){
		super("Este usuario no cuenta con permisos para realizar revisiones");
	}

}
