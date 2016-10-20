package conf.gestorpersistance;

import org.hsqldb.server.Server;

import conf.generadores.GeneradorArchivosJDBC;
import conf.generadores.GeneradorArchivosJPA;
import conf.util.BusinessException;

/**
 * 
 * GestorEmbeddedBD ejecuta server de base de datos embebidas en la aplicación.
 * Unicamente se puede tener una instancia de cada base de datos por JVM
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class GestorEmbeddedBD {

	private static Server hsqlServer;

	public static void runDerbyJDBC(String databasename) throws BusinessException {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			GeneradorArchivosJDBC.start("jdbc:derby:" + databasename + ";create=true", "", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Error al iniciar la base de datos embebida derby");
		}
	}

	public static void runHSQLDBJPA(String databasename) throws BusinessException {
		if (hsqlServer == null) {
			runHSQLDB(databasename);
			GeneradorArchivosJPA.startHSQLDB("jdbc:hsqldb:hsql://localhost/" + databasename, "sa", "");
		}
	}

	public static void runHSQLDBJDBC(String databasename) throws BusinessException {
		if (hsqlServer == null) {
			runHSQLDB(databasename);
			GeneradorArchivosJDBC.start("jdbc:hsqldb:hsql://localhost/" + databasename, "sa", "");
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
		try{
			hsqlServer.stop();
			hsqlServer = null;
		}catch(NullPointerException e){} //pass
	}

}