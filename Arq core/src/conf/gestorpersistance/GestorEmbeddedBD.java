package conf.gestorpersistance;

import org.hsqldb.server.Server;

import conf.generadores.GeneradorArchivosConexion;
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

	public static void main(String[] args) throws BusinessException {
		if (args[0].equals("derby")) {
			runDerby(args[1]);
		} else {
			runHSQLDB(args[2], args[3]);
		}
	}

	public static void runDerby(String databasename) throws BusinessException {
		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			GeneradorArchivosConexion.run("jdbc:derby:" + databasename + ";create=true", "", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Error al iniciar la base de datos embebida derby");
		}
	}

	public static void runHSQLDB(String databasename, String databasefilename) {
		if (hsqlServer == null) {
			hsqlServer = new Server();
			hsqlServer.setLogWriter(null);
			hsqlServer.setSilent(true);
			hsqlServer.setDatabaseName(0, databasename);
			hsqlServer.setDatabasePath(0, "file:" + databasefilename);
			hsqlServer.start();
		}
	}

	public static void stopHSQLDB() {
		hsqlServer.stop();
		hsqlServer = null;
	}

}
