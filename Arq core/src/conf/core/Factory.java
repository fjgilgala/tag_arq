package conf.core;

/**
 * 
 * Factory encargada de crear las factorias de las clases Persistence y
 * Business. No representa un punto de entrada hacia la aplicación del usuario,
 * vease @Service.
 * 
 * @author Francisco Javier Gil Gala
 *
 */
class Factory {

	private static Persistence _persistence;
	private static Business _business;

	/**
	 * Devuelve una instancia de la implementación concreta de Persistence
	 * @return Persistence
	 */
	static Persistence persistence() {
		return _persistence;
	}

	/**
	 * Devuelve una instancia de la implementación cocnreta de Business
	 * @return Business
	 */
	static Business business() {
		return _business;
	}

	public static void _setImplePersistence(Persistence persistence) {
		_persistence = PersistenceFactory.setPersistence(persistence);
	}

	public static void _setImpleBusiness(Business business) {
		_business = BusinessFactory.setBusiness(business);
	}
}