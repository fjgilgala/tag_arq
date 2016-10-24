package conf.framework.jdbc.core;

import conf.util.BusinessException;

public class JDBCFactory {

	private static boolean pool = false;

	public static void onPool() {
		pool = true;
	}

	public static void offPool() {
		pool = true;
	}

	/**
	 * Devuelve una instancia concreta de la clase JDBC
	 * 
	 * @return JDBC
	 * @throws BusinessException
	 */
	public static JDBC getJDBC() throws BusinessException {
		if (!pool)
			return SimpleJDBC.getInstance();
		else
			return PoolJDBC.getInstance();
	}

}
