package conf.core;

import conf.gc.CompilarClases;
import conf.gc.GenerarClases;
import conf.util.BusinessException;

/**
 * 
 * Service es la clase que sirve de entrada al framework. Gestiona los accesos
 * al resto de clases. Es el único punto de entrada.
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class Service {

	public static Business services = Factory.business();
	public static Persistence persistence = Factory.persistence();

	/**
	 * Inicia el framework
	 * 
	 * @param businessImpl
	 * @param persistenceImpl
	 */
	public static void start(Business businessImpl, Persistence persistenceImpl) {
		start(businessImpl);
		start(persistenceImpl);
	}

	/**
	 * Inicializa la interfaz de negocio
	 * 
	 * @param businessImpl
	 */
	public static void start(Business businessImpl) {
		if (Service.services == null)
			Factory._setImpleBusiness(businessImpl);
	}

	/**
	 * Inicializa la inetrfaz de persistencia
	 * 
	 * @param persistenceImpl
	 */
	public static void start(Persistence persistenceImpl) {
		if (Service.persistence == null)
			Factory._setImplePersistence(persistenceImpl);
	}

	/**
	 * Crea una serie de clases demo para poder utilizar el framework. Es
	 * necesario refrescar el proyecto o volver a compilar para poder seguir con
	 * la ejecución del programa.
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static void startDemo() throws BusinessException {
		GenerarClases.example();
		CompilarClases.compilar("src/business", "BusinessImpl.java");
		Class<Business> business;
		Class<Persistence> persistence;
		try {
			business = (Class<Business>) Class.forName("business.BusinessImpl");
			persistence = (Class<Persistence>) Class.forName("persistence.PersistenceImpl");
			start(business.newInstance(), persistence.newInstance());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new BusinessException(
					"Error al instanciar las clases generadas; refresque el proyecto y vuelva a compilar");
		}
	}

}