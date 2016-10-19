package conf.framework.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

import conf.util.BusinessException;
import conf.util.Lector;

public class PoolJDBC extends JDBC {

	private static DataSource pooled;

	protected PoolJDBC() throws BusinessException {
		super();
	}

	// singleton
	protected static PoolJDBC instancia = null;

	public static PoolJDBC getInstance() throws BusinessException {
		if (instancia == null)
			createInstance();
		return instancia;
	}

	private static void createInstance() throws BusinessException {
		if (instancia == null)
			synchronized (PoolJDBC.class) {
				if (instancia == null)
					instancia = new PoolJDBC();
			}
	} // fin singleton

	@Override
	public Connection pedirConexion() throws BusinessException {
		try {
			return pooled.getConnection();
		} catch (SQLException e) {
			throw new BusinessException("No se puede recuperar la conexion");
		}
	}

	@Override
	protected void crearConexion() throws SQLException, IOException {
		DataSource unpooled = DataSources.unpooledDataSource(url, userName, password);
		Map<String, Object> overrides = new HashMap<String, Object>();
		overrides.put("maxStatements", Lector.loadProperty(CONFIG_FILE, "maxStatements"));
		overrides.put("maxPoolSize", Lector.loadProperty(CONFIG_FILE, "maxPoolSize"));
		overrides.put("minPoolSize", Lector.loadProperty(CONFIG_FILE, "minPoolSize"));
		overrides.put("initialPoolSize", Lector.loadProperty(CONFIG_FILE, "initialPoolSize"));
		pooled = DataSources.pooledDataSource(unpooled, overrides);
	}

}
