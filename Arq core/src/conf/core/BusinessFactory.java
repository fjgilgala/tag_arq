package conf.core;

/**
 * 
 * BusinessFactory factoria de clases encargada de crear la implementación
 * concreta para la capa de lógica
 * 
 * @author Francisco Javier Gil Gala
 *
 */
class BusinessFactory {

	public static Business _business;

	public static Business setBusiness(Business business) {
		if (_business == null)
			BusinessFactory._business = business;
		return _business;
	}

}
