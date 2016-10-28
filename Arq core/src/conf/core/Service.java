package conf.core;

import javax.persistence.EntityManager;

import conf.framework.jdbc.core.JDBC;
import conf.framework.jdbc.core.JDBCFactory;
import conf.framework.jpa.core.Jpa;
import conf.framework.rest.ServiceREST;
import conf.generadores.GeneradorDemo;
import conf.generadores.UtilGenerador;
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

	public static void main(String[] args) throws BusinessException {
		ServiceREST.start("src/business/");
	}

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
		UtilGenerador.modificarRutaPaquetes(ruta);
		generarDemoJPA();
	}

	/**
	 * Genera los paquetes y clases para una demo JPA en la ruta src/.. p.e:
	 * src/business
	 * 
	 * @throws BusinessException
	 */
	public static void generarDemoJPA() throws BusinessException {
		try {
			GeneradorDemo.generarDemoJPA();
		} catch (BusinessException e) {
			logExcepction("Error al generar la demo JPA", e);
			throw new BusinessException(e);
		}
		logForzoso("Generando clases y archivos para la demo JPA");
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
	 * @param boolean
	 *            activatePool
	 * 
	 * @throws BusinessException
	 */
	public void generarDemoJDBC(boolean activatePool, String ruta) throws BusinessException {
		UtilGenerador.modificarRutaPaquetes(ruta);
		generarDemoJDBC(activatePool);
	}

	/**
	 * Genera los paquetes y clases para una demo JDBC en la ruta src/.. p.e:
	 * src/business
	 * 
	 * @param boolean
	 *            activatePool
	 * @throws BusinessException
	 */
	public static void generarDemoJDBC(boolean activatePool) throws BusinessException {
		try {
			if (activatePool)
				JDBCFactory.onPool();
			GeneradorDemo.generarDemoJDBC();
		} catch (BusinessException e) {
			logExcepction("Error al generar la demo JPA", e);
			throw new BusinessException(e);
		}
		logForzoso("Generando clases y archivos para la demo JDBC");
		generarDemo();
	}

	private static void generarDemo() {
		logForzoso("Clases generadas");
		startDemo();
		try {
			logForzoso("OK: comunicacion business->persistencia");
			logForzoso("OK: comunicacion persistencia->business");
		} catch (NullPointerException e) {
		}
	}

	@SuppressWarnings("unchecked")
	private static void startDemo() {
		Class<Business> business;
		Class<Persistence> persistence;
		try {
			business = (Class<Business>) Class.forName(UtilGenerador.getRutaPaquetesJava() + "business.BusinessImpl");
			persistence = (Class<Persistence>) Class
					.forName(UtilGenerador.getRutaPaquetesJava() + "persistence.PersistenceImpl");
			start(business.newInstance(), persistence.newInstance());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logForzoso("Todo correcto: Refresque el proyecto y vuelva a compilar");
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
		try {
			GestorEmbeddedBD.runHSQLDBJPA(databasename);
		} catch (BusinessException e) {
			logExcepction("Error al generar la base de datos embebida HSQL para JPA", e);
			throw new BusinessException(e);
		}
		log("Generada una base de datos HSQL embebida y los archivos necesarios para una conexión JPA");
	}

	/**
	 * Inicia una base de datos HSQL embebida en la aplicación con todos los
	 * archivos necesarios para JDBC
	 * 
	 * @param databasename
	 * @throws BusinessException
	 */
	public static void generateHSQLServerJDBC(String databasename) throws BusinessException {
		try {
			GestorEmbeddedBD.runHSQLDBJDBC(databasename);
		} catch (BusinessException e) {
			logExcepction("Error al generar la base de datos embebida HSQL para JDBC", e);
			throw new BusinessException(e);
		}
		log("Generada una base de datos HSQL embebida y los archivos necesarios para una conexión JDBC");
	}

	/**
	 * Para la base de datos HSQL embebida
	 */
	public static void stopHSQLServer() {
		GestorEmbeddedBD.stopHSQLDB();
		log("Parado el gestor de bases de datos HSQL embebida");
	}

	/**
	 * Inicia una base de datos Derby embebida en la aplicación con todos los
	 * archivos necesarios para JDBC
	 * 
	 * @param databasename
	 * @throws BusinessException
	 */
	public static void generateDerbyDBServerJDBC(String databasename) throws BusinessException {
		try {
			GestorEmbeddedBD.runDerbyJDBC(databasename);
		} catch (BusinessException e) {
			logExcepction("Error al generar la base de datos embebida Derby para JDBC", e);
			throw new BusinessException(e);
		}
		log("Generada una base de datos Derby embebida y los archivos necesarios para una conexión JDBC");
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
			log("Accediendo a la factoria de factorias");
			return Factory.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logExcepction("Error al acceder a la factoria de factorias: No se puede recuperar el servicio", e);
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
		log("Iniciado el framework");
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
	 * @return EntityManager
	 */
	public static EntityManager JpaManager() {
		log("Accediendo al EntityManager - JPA");
		return Jpa.getManager();
	}

	/**
	 * Devuelve una implementación de JDBC con el que hacer las acciones de
	 * persistencia con JDBC, solo para el framework JDBC
	 * 
	 * @return JDBC
	 * @throws BusinessException
	 */
	public static JDBC getJDBC() throws BusinessException {
		log("Accediendo a la implementación de JDBC");
		return JDBCFactory.getJDBC();
	}

	/**
	 * Establece activa un pool de conexiones, solo para el framework JDBC
	 */
	public static void onPoolConexionesJDBC() {
		JDBCFactory.onPool();
		log("Activado pool de conexiones JDBC");
	}

	/**
	 * Establece desactiva un pool de conexiones, solo para el framework JDBC
	 */
	public static void oFFPoolConexionesJDBC() {
		JDBCFactory.offPool();
		log("Desactivado pool de conexiones JDBC");
	}
	// fin otros

	// logger
	public final static int MODE_DISCRETO = LoggerImpl.MODE_DISCRETO;
	public final static int MODE_ACTIVO = LoggerImpl.MODE_ACTIVO;
	public final static int MODE_OFF = LoggerImpl.MODE_OFF;

	public static void changeModeLog(int mode) {
		LoggerImpl.setMode(mode);
	}

	public static int modeLogDiscreto() {
		return LoggerImpl.setMode(MODE_DISCRETO);
	}

	public static int modeLogActivo() {
		return LoggerImpl.setMode(MODE_ACTIVO);
	}

	public static int modeLogOff() {
		return LoggerImpl.setMode(MODE_OFF);
	}

	private static void log(String line) {
		LoggerImpl.log(line);
	}

	private static void logExcepction(String line, Exception e) {
		LoggerImpl.log(line);
		LoggerImpl.log(e.getMessage());
	}

	private static void logForzoso(String line) {
		log("Log forzoso");
		int mode = LoggerImpl.getMode();
		LoggerImpl.modeActivo();
		log(line);
		LoggerImpl.setMode(mode);
		log("Fin log forzoso");
	}
	// fin logger
}