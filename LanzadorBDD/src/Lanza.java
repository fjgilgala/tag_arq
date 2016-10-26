import java.sql.SQLException;

import conf.core.Service;
import conf.framework.jdbc.core.JDBCFactory;
import conf.util.BusinessException;

/**
 * Lanza una BDD para ser ser usada por los ejemplos
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class Lanza {

	public static void main(String[] args) throws SQLException, BusinessException {
		String databasename = "internal";
		Service.generateHSQLServerJDBC(databasename);
		Service.getJDBC().pedirConexion().setAutoCommit(true);
		// JDBCFactory.getJDBC().pedirConexion().createStatement().execute("DROP TABLE libro;");
		JDBCFactory.getJDBC().pedirConexion().createStatement()
				.execute("create table libro(id INTEGER IDENTITY PRIMARY KEY, titulo varchar(50), autor varchar(50))");
		System.out.println("Base de datos en ejecucion");
	}
}
