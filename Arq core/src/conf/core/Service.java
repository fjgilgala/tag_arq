package conf.core;

import conf.generadores.GeneradorCodigo;
import conf.generadores.GeneradorJPAFile;
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
		GeneradorJPAFile.start();
	}

	/**
	 * Genera los paquetes y clases para una demo a partir de una ruta de
	 * paquetes especificada p.e: teleco/espartano/ por defecto la ruta se
	 * generara como src/teleco/espartano/ para los paquetes
	 * src/teleco/espartano/business etc
	 * 
	 * @param String
	 *            ruta
	 * @throws BusinessException
	 */
	public void generarDemo(String ruta) throws BusinessException {
		GeneradorCodigo.modificarRutaPaquetes(ruta);
		generarDemo();
	}

	/**
	 * Genera los paquetes y clases para una demo en la ruta src/.. p.e:
	 * src/business
	 * 
	 * @throws BusinessException
	 */
	public void generarDemo() throws BusinessException {
		startDemo();
		get().business().testComunicaPresentacion();
		get().persistence().testComunicaBusiness();
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
	}

	/**
	 * Inicializa la inetrfaz de persistencia
	 * 
	 * @param persistenceImpl
	 */
	public void start(Persistence persistenceImpl) {
		Factory._setImplePersistence(persistenceImpl);
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
			business = (Class<Business>) Class.forName(GeneradorCodigo.getRutaPaquetesJava() + "business.BusinessImpl");
			persistence = (Class<Persistence>) Class
					.forName(GeneradorCodigo.getRutaPaquetesJava() + "persistence.PersistenceImpl");
			start(business.newInstance(), persistence.newInstance());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new BusinessException(
					"Error al instanciar las clases generadas; refresque el proyecto y vuelva a compilar");
		}
	}

}