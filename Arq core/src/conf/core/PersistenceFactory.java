package conf.core;

/**
 * 
 * PersistenceFactory factoria de clases encargada de crear la implementación
 * concreta para la persistencia
 * 
 * @author Francisco Javier Gil Gala
 *
 */
class PersistenceFactory {

	static Persistence _persistence;

	private PersistenceFactory() {
	}

	static Persistence setPersistence(Persistence persistence) {
		if (PersistenceFactory._persistence == null)
			PersistenceFactory._persistence = persistence;
		return _persistence;
	}
}
