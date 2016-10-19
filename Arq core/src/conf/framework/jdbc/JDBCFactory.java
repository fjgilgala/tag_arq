package conf.framework.jdbc;

import conf.util.BusinessException;

public class JDBCFactory {

	private static boolean pool = false;

	public static void onPool() {
		pool = true;
	}
	
	public static void offPool() {
		pool = true;
	}

	public static JDBC getJDBC() throws BusinessException {
		if (pool)
			return SimpleJDBC.getInstance();
		else
			return PoolJDBC.getInstance();
	}

}
