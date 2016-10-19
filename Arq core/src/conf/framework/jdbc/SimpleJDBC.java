package conf.framework.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import conf.util.BusinessException;

public class SimpleJDBC extends JDBC {

	protected static Connection con;

	protected SimpleJDBC() throws BusinessException {
		super();
	}

	// singleton
	protected static SimpleJDBC instancia = null;

	public static SimpleJDBC getInstance() throws BusinessException {
		if (instancia == null)
			createInstance();
		return instancia;
	}

	private static void createInstance() throws BusinessException {
		if (instancia == null)
			synchronized (SimpleJDBC.class) {
				if (instancia == null)
					instancia = new SimpleJDBC();
			}
	} // fin singleton

	@Override
	public Connection pedirConexion() throws BusinessException {
		return con;
	}

	@Override
	protected void crearConexion() throws SQLException, IOException {
		con = DriverManager.getConnection(url, userName, password);
	}

}
