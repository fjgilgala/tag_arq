package conf.util;

/**
 * Clase interna para controlar los errores de la aplicación. Puede usarse para
 * cualquier proposito dentro de la aplicación. BusinessException
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 4001710687990554589L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
