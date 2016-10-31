package conf.core;

import java.io.IOException;

import javax.persistence.EntityManager;

import conf.framework.jdbc.core.JDBC;
import conf.framework.jdbc.core.JDBCFactory;
import conf.framework.jpa.core.Jpa;
import conf.framework.sgbd.ServiceSGBD;
import conf.generadores.GeneradorDemo;
import conf.generadores.UtilGenerador;
import conf.generadores.rest.GeneradorREST;
import conf.util.BusinessException;
import conf.util.Escritor;
import conf.util.Lector;
import conf.util.LoggerImpl;

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
	 */
	public static void generarDemoJPA(String ruta) {
		UtilGenerador.modificarRutaPaquetes(ruta);
		generarDemoJPA();
	}

	/**
	 * Genera los paquetes y clases para una demo JPA en la ruta src/.. p.e:
	 * src/business
	 * 
	 */
	public static void generarDemoJPA() {
		try {
			GeneradorDemo.generarDemoJPA();
			logForzoso("Generando clases y archivos para la demo JPA");
			generarDemo();
		} catch (BusinessException e) {
			logExcepction("Error al generar la demo JPA", e);
			throw new RuntimeException("Error al inicializar la demo: " + e);
		}
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
	 */
	public void generarDemoJDBC(boolean activatePool, String ruta) {
		UtilGenerador.modificarRutaPaquetes(ruta);
		generarDemoJDBC(activatePool);
	}

	/**
	 * Genera los paquetes y clases para una demo JDBC en la ruta src/.. p.e:
	 * src/business
	 * 
	 * @param boolean
	 *            activatePool
	 */
	public static void generarDemoJDBC(boolean activatePool) {
		try {
			if (activatePool)
				JDBCFactory.onPool();
			GeneradorDemo.generarDemoJDBC();
			logForzoso("Generando clases y archivos para la demo JDBC");
			generarDemo();
		} catch (BusinessException e) {
			logExcepction("Error al generar la demo JDBC", e);
			throw new RuntimeException("Error al inicializar la demo: " + e);
		}
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
	 */
	public static void generateHSQLServerJPA(String databasename) {
		try {
			ServiceSGBD.runHSQLDBJPA(databasename);
			log("Generada una base de datos HSQL embebida y los archivos necesarios para una conexión JPA");
		} catch (BusinessException e) {
			logExcepction("Error al generar la base de datos embebida HSQL para JPA", e);
			throw new RuntimeException("Error al inicializar la base de datos: " + e);
		}
	}

	/**
	 * Inicia una base de datos HSQL embebida en la aplicación con todos los
	 * archivos necesarios para JDBC
	 * 
	 * @param databasename
	 */
	public static void generateHSQLServerJDBC(String databasename) {
		try {
			ServiceSGBD.runHSQLDBJDBC(databasename);
			log("Generada una base de datos HSQL embebida y los archivos necesarios para una conexión JDBC");
		} catch (BusinessException e) {
			logExcepction("Error al generar la base de datos embebida HSQL para JDBC", e);
			throw new RuntimeException("Error al inicializar la base de datos: " + e);
		}
	}

	/**
	 * Para la base de datos HSQL embebida
	 */
	public static void stopHSQLServer() {
		ServiceSGBD.stopHSQLDB();
		log("Parado el gestor de bases de datos HSQL embebida");
	}

	/**
	 * Inicia una base de datos Derby embebida en la aplicación con todos los
	 * archivos necesarios para JDBC
	 * 
	 * @param databasename
	 */
	public static void generateDerbyDBServerJDBC(String databasename) {
		try {
			ServiceSGBD.runDerbyJDBC(databasename);
			log("Generada una base de datos Derby embebida y los archivos necesarios para una conexión JDBC");
		} catch (BusinessException e) {
			logExcepction("Error al generar la base de datos embebida Derby para JDBC", e);
			throw new RuntimeException("Error al inicializar la base de datos: " + e);
		}
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
	 * Inicia el framework a partir del archivo de propiedades
	 * arq_core.properties
	 * 
	 */
	public static void start() {
		try {
			Lector.loadProperty("/arq_core.properties", "business");
			Lector.loadProperty("/arq_core.properties", "persistence");
			log("Iniciado el framework");
		} catch (IOException e) {
			logExcepction("Error al acceder al archivo de configuracion del framwork", e);
			throw new RuntimeException("Error al inicializar el framework: " + e);
		} catch (BusinessException e) {
			throw new RuntimeException(e + ", " + "es posible que el fichero no se encuentre creado");
		}
	}

	/**
	 * Inicia el framework
	 * 
	 * @param businessImpl
	 * @param persistenceImpl
	 */
	public static void start(Business businessImpl, Persistence persistenceImpl) {
		try {
			start(businessImpl);
			start(persistenceImpl);
			log("Iniciado el framework");
			guardaConf();
		} catch (BusinessException e) {
			throw new RuntimeException("Error al inicializar el framework: " + e);
		}
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

	private static void guardaConf() throws BusinessException {
		try {
			log("Generando archivo properties del framework: arq_core.properties");
			String body = "business=" + UtilGenerador.getRutaPaquetesJava() + get().business().getClass().getName()
					+ "\n";
			body += "persistence=" + UtilGenerador.getRutaPaquetesJava() + get().persistence().getClass().getName()
					+ "\n";
			Escritor.escritorForzoso("src/", "arq_core.properties", body);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public static void fin() {
		try {
			Service.class.newInstance().finalize();
			System.runFinalization();
			System.gc();
		} catch (Throwable e) { // no
		}
	}

	@Override
	protected void finalize() throws Throwable {
		log("Ejecución finalizada");
		super.finalize();
	}

	// fin framework
	// persistencia JDBC / JPA
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

	// fin persistencia JBDC / JPA
	// logger
	public final static int MODE_DISCRETO = LoggerImpl.MODE_DISCRETO;
	public final static int MODE_ACTIVO = LoggerImpl.MODE_ACTIVO;
	public final static int MODE_OFF = LoggerImpl.MODE_OFF;

	public static void changeModeLog(int mode) {
		LoggerImpl.setMode(mode);
	}

	public static int modeLogDiscreto() {
		logForzoso("Activando el modo discreto");
		return LoggerImpl.setMode(MODE_DISCRETO);
	}

	public static int modeLogActivo() {
		logForzoso("Activando el modo activo");
		return LoggerImpl.setMode(MODE_ACTIVO);
	}

	public static int modeLogOff() {
		logForzoso("Activando el modo apagado");
		return LoggerImpl.setMode(MODE_OFF);
	}

	public static void log(String line) {
		LoggerImpl.log(line);
	}

	public static void logExcepction(String line, Exception e) {
		LoggerImpl.log(line);
		LoggerImpl.log(e.getMessage());
	}

	public static void logForzoso(String line) {
		int mode = LoggerImpl.getMode();
		LoggerImpl.modeActivo();
		log(line);
		LoggerImpl.setMode(mode);
	}

	// fin logger
	// servicios REST
	/**
	 * Genera las interfaces y clases necesarias para todas las clases del
	 * directorio pasado por parámetro en el directorio definido por la ruta por
	 * defecto (src/../rest/)
	 * 
	 */
	public static void generaClasesRest(String directorio) {
		try {
			GeneradorREST.start(directorio);
			logForzoso("Generando clases para implementar servicios REST");
		} catch (BusinessException e) {
			logExcepction("Generando clases para implementar servicios REST", e);
			throw new RuntimeException("Error al inicializar la demo: " + e);
		}
	}
	// fin servicios REST
}