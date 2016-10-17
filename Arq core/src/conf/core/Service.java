package conf.core;

import conf.gc.GeneradorCodigo;
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

	public static void main(String[] args) throws BusinessException {
		System.out.println("--------------\n - configurando el framework - ");
		Service s = new Service();
		s.startDemo();
		s.get().business().testComunicaPresentacion();
		s.get().persistence().testComunicaBusiness();
		System.out.println("--------------\n - todo ok - ");
	}

	/**
	 * Devuelve una instancia de la factory que permite hacer las llamadas a los
	 * servicios de la aplicacion
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public Factory get() throws BusinessException {
		try {
			return Factory.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BusinessException("No se puede recuperar el servicio");
		}
	}

	/**
	 * Inicia el framework
	 * 
	 * @param businessImpl
	 * @param persistenceImpl
	 */
	public void start(Business businessImpl, Persistence persistenceImpl) {
		start(businessImpl);
		start(persistenceImpl);
	}

	/**
	 * Inicializa la interfaz de negocio
	 * 
	 * @param businessImpl
	 */
	public void start(Business businessImpl) {
		Factory._setImpleBusiness(businessImpl);
		System.out.println("Enlazada la capa de negocio");
	}

	/**
	 * Inicializa la inetrfaz de persistencia
	 * 
	 * @param persistenceImpl
	 */
	public void start(Persistence persistenceImpl) {
		Factory._setImplePersistence(persistenceImpl);
		System.out.println("Enlazada la capa de persistencia");
	}

	/**
	 * Crea una serie de clases demo para poder utilizar el framework. Es
	 * necesario refrescar el proyecto o volver a compilar para poder seguir con
	 * la ejecución del programa.
	 * 
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public void startDemo() throws BusinessException {
		GeneradorCodigo.generarDemo();
		Class<Business> business;
		Class<Persistence> persistence;
		try {
			business = (Class<Business>) Class.forName("business.BusinessImpl");
			persistence = (Class<Persistence>) Class.forName("persistence.PersistenceImpl");
			start(business.newInstance(), persistence.newInstance());
			System.out.println("Clases de prueba instanciadas correctamente");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new BusinessException(
					"Error al instanciar las clases generadas; refresque el proyecto y vuelva a compilar");
		}
	}

}