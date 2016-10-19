package conf.framework.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import conf.util.BusinessException;

/**
 * Clase que implementa las operaciones básicas de acceso a JDBC que debe ser
 * implementada por las operaciones de logica
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public abstract class Action {

	protected Connection c;

	/**
	 * Implementa la operación principal de la clase que suministra el servicio
	 * 
	 * @throws BusinessException
	 */
	public abstract Object execute() throws BusinessException;

	/**
	 * Establace una conexión dada por el pool de conexiones de la clase
	 * Conexion
	 * 
	 * @throws BusinessException
	 */
	public void establecerConexion() throws BusinessException {
		try {
			c = JDBCFactory.getJDBC().pedirConexion();
		} catch (BusinessException e) {
			throw new BusinessException("Error con recuperar la conexión");
		}
	}

	/**
	 * Cierra la conexión
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void cerrarConexion() throws BusinessException {
		try {
			JDBCFactory.getJDBC().close(c);
		} catch (BusinessException e) {
			throw new BusinessException("Error al cerrar la conexión");
		}
	}

	/**
	 * Pone que la conexión haga auto commit
	 * 
	 * @throws BusinessException
	 */
	public void setAutoCommit() throws BusinessException {
		try {
			c.setAutoCommit(true);
		} catch (SQLException e) {
			throw new BusinessException("No se puede establecer el autocommit");
		}
	}

	/**
	 * Hace que la aplicación no ponga auto commit
	 * 
	 * @throws BusinessException
	 */
	public void removeAutoCommit() throws BusinessException {
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			throw new BusinessException("No se puede establecer el no autocommit");
		}
	}

}
