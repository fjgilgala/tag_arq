package conf.core;

/**
 * 
 * Persistence clase abstracta de la que se debe extender la funcionalidad para
 * suministrar con ella los servicios de la capa de persistencia al resto de
 * capas.
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public abstract class Persistence {

	public Persistence() {
		Factory._setImplePersistence(this);
	}

}
