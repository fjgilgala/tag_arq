package conf.framework.sgbd;

import org.hsqldb.server.Server;

import conf.generadores.persistence.GeneradorJDBC;
import conf.generadores.persistence.GeneradorJPA;
import conf.util.BusinessException;

/**
 * 
 * ServiceSGBD ejecuta server de base de datos embebidas en la aplicación.
 * Unicamente se puede tener una instancia de cada base de datos por JVM
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class ServiceSGBD {

	private static Server hsqlServer;

	public static void runDerbyJDBC(String databasename) throws BusinessException {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			GeneradorJDBC.start("jdbc:derby:" + databasename + ";create=true", "", "");
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Error al iniciar la base de datos embebida derby");
		}
	}

	public static void runHSQLDBJPA(String databasename) throws BusinessException {
		try {
			if (hsqlServer == null) {
				runHSQLDB(databasename);
				GeneradorJPA.startHSQLDB("jdbc:hsqldb:hsql://localhost/" + databasename, "sa", "");
			}
		} catch (BusinessException e) {
			throw new BusinessException("Error al iniciar la base de datos embebida HSQLDB\n" + e.getMessage());
		}
	}

	public static void runHSQLDBJDBC(String databasename) throws BusinessException {
		try {
			if (hsqlServer == null) {
				runHSQLDB(databasename);
				GeneradorJDBC.start("jdbc:hsqldb:hsql://localhost/" + databasename, "sa", "");
			}
		} catch (BusinessException e) {
			throw new BusinessException("Error al iniciar la base de datos embebida HSQLDB\n" + e.getMessage());
		}
	}

	private static void runHSQLDB(String databasename) throws BusinessException {
		if (hsqlServer == null) {
			hsqlServer = new Server();
			hsqlServer.setLogWriter(null);
			hsqlServer.setSilent(true);
			hsqlServer.setDatabaseName(0, databasename);
			hsqlServer.setDatabasePath(0, "file:" + databasename);
			hsqlServer.start();
		}
	}

	public static void stopHSQLDB() {
		try {
			hsqlServer.stop();
			hsqlServer = null;
		} catch (NullPointerException e) {
		} // pass
	}

}