package conf.framework.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conf.util.BusinessException;

/**
 * Gateway implementa con operaciones básicas de conexión con un driver JDBC
 * 
 * @author Francisco Javier Gil Gala
 */
public class Gateway {

	protected Connection c;
	protected PreparedStatement pst;
	protected ResultSet rs;

	/**
	 * Establece un objeto Connection para ser usado para los métodos de
	 * consulta del Gateway
	 * 
	 * @param conexion
	 */
	public void setConexion(Connection conexion) {
		this.c = conexion;
	}

	/**
	 * Establece valor a un PreparedStatement
	 * 
	 * @param String
	 *            nombre_consulta
	 * @throws RIException
	 */
	public void prepararStatement(String nombre_consulta) throws BusinessException {
		try {
			pst = c.prepareStatement(SimpleJDBC.getInstance().getConsulta(nombre_consulta));
		} catch (SQLException e) {
			throw new BusinessException("Existe un error con la base de datos");
		}
	}

}
