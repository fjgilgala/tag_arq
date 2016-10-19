package conf.core;

import conf.generadores.GeneradorCodigo;
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

	// demo
	/**
	 * Genera los paquetes y clases para una demo JPA a partir de una ruta de
	 * paquetes especificada p.e: teleco/espartano/ por defecto la ruta se
	 * generara como src/teleco/espartano/ para los paquetes
	 * src/teleco/espartano/business etc
	 * 
	 * @param String
	 *            ruta
	 * @throws BusinessException
	 */
	public void generarDemoJPA(String ruta) throws BusinessException {
		GeneradorCodigo.modificarRutaPaquetes(ruta);
		generarDemoJPA();
	}

	/**
	 * Genera los paquetes y clases para una demo JPA en la ruta src/.. p.e:
	 * src/business
	 * 
	 * @throws BusinessException
	 */
	public void generarDemoJPA() throws BusinessException {
		GeneradorCodigo.generarDemoJPA();
		generarDemo();
	}

	/**
	 * Genera los paquetes y clases para una demo JDBC a partir de una ruta de
	 * paquetes especificada p.e: teleco/espartano/ por defecto la ruta se
	 * generara como src/teleco/espartano/ para los paquetes
	 * src/teleco/espartano/business etc
	 * 
	 * @param String
	 *            ruta
	 * @throws BusinessException
	 */
	public void generarDemoJDBC(String ruta) throws BusinessException {
		GeneradorCodigo.modificarRutaPaquetes(ruta);
		generarDemoJDBC();
	}

	/**
	 * Genera los paquetes y clases para una demo JDBC en la ruta src/.. p.e:
	 * src/business
	 * 
	 * @throws BusinessException
	 */
	public void generarDemoJDBC() throws BusinessException {
		GeneradorCodigo.generarDemoJDBC();
		generarDemo();
	}

	private void generarDemo() throws BusinessException {
		System.out.println("Generando paquetes y clases");
		startDemo();
		get().business().testComunicaPresentacion();
		get().persistence().testComunicaBusiness();
	}

	@SuppressWarnings("unchecked")
	private void startDemo() {
		Class<Business> business;
		Class<Persistence> persistence;
		try {
			business = (Class<Business>) Class.forName(GeneradorCodigo.getRutaPaquetesJava() + "business.BusinessImpl");
			persistence = (Class<Persistence>) Class
					.forName(GeneradorCodigo.getRutaPaquetesJava() + "persistence.PersistenceImpl");
			start(business.newInstance(), persistence.newInstance());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.out.println("Todo correcto\nRefresque el proyecto y vuelva a compilar");
		}
	}
	// fin demo

	// framework
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
	// fin framework
}