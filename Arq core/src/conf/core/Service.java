package conf.core;

import javax.persistence.EntityManager;

import conf.framework.jpa.core.Jpa;
import conf.generadores.GeneradorCodigo;
import conf.gestorpersistance.GestorEmbeddedBD;
import conf.util.BusinessException;

/**
 * 
 * Service es la clase que sirve de entrada al framework. Gestiona los accesos
 * al resto de clases.
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
	public static void generarDemoJPA(String ruta) throws BusinessException {
		GeneradorCodigo.modificarRutaPaquetes(ruta);
		generarDemoJPA();
	}

	/**
	 * Genera los paquetes y clases para una demo JPA en la ruta src/.. p.e:
	 * src/business
	 * 
	 * @throws BusinessException
	 */
	public static void generarDemoJPA() throws BusinessException {
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
	public static void generarDemoJDBC() throws BusinessException {
		GeneradorCodigo.generarDemoJDBC();
		generarDemo();
	}

	private static void generarDemo() throws BusinessException {
		System.out.println("Generando paquetes y clases");
		startDemo();
		try {
			get().business().testComunicaPresentacion();
			get().persistence().testComunicaBusiness();
		} catch (NullPointerException e) {
		}
	}

	@SuppressWarnings("unchecked")
	private static void startDemo() {
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

	// gestor base de datos
	/**
	 * Inicia una base de datos HSQLembebida en la aplicación con todos los
	 * archivos necesarios para JPA
	 * 
	 * @param databasename
	 * @throws BusinessException
	 */
	public static void generateHSQLServerJPA(String databasename) throws BusinessException {
		GestorEmbeddedBD.runHSQLDBJPA(databasename);
	}

	/**
	 * Inicia una base de datos HSQL embebida en la aplicación con todos los
	 * archivos necesarios para JDBC
	 * 
	 * @param databasename
	 * @throws BusinessException
	 */
	public static void generateHSQLServerJDBC(String databasename) throws BusinessException {
		GestorEmbeddedBD.runHSQLDBJDBC(databasename);
	}

	/**
	 * Para la base de datos HSQL embebida
	 */
	public static void stopHSQLServer() {
		GestorEmbeddedBD.stopHSQLDB();
	}

	/**
	 * Inicia una base de datos Derby embebida en la aplicación con todos los
	 * archivos necesarios para JDBC
	 * 
	 * @param databasename
	 * @throws BusinessException
	 */
	public static void generateDerbyDBServerJDBC(String databasename) throws BusinessException {
		GestorEmbeddedBD.runDerbyJDBC(databasename);
	}
	// fin gestor base de datos

	// framework
	/**
	 * Devuelve una instancia de la factory que permite hacer las llamadas a los
	 * servicios de la aplicacion
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public static Factory get() throws BusinessException {
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
		Factory._setImpleBusiness(businessImpl);
	}

	/**
	 * Inicializa la inetrfaz de persistencia
	 * 
	 * @param persistenceImpl
	 */
	public static void start(Persistence persistenceImpl) {
		Factory._setImplePersistence(persistenceImpl);
	}
	// fin framework

	// otros
	/**
	 * Devuelve un EntityManager con el que hacer las acciones de persistencia
	 * con JPA, solo para el framework JPA
	 * 
	 * @return
	 */
	public static EntityManager JpaManager() {
		return Jpa.getManager();
	}
	// fin otros
}