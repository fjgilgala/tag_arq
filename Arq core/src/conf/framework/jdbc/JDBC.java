package conf.framework.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conf.util.BusinessException;
import conf.util.Lector;

/**
 * 
 * Conexion Clase única para toda la aplicación y sus multiples sesiones
 * implementada mediante un Singleton que lee desde el archivo de propiedades
 * 'conexion.properties' para establecer los valores de la conexión, crear una
 * conexión y configurar el fichero de consultas.
 * 
 * Tambien provee los servicios:
 * 
 * @Method pedirConexionPool()
 * @Mehtod getConsulta(NombreConsulta)
 * @Method close(ResultSet, Statement, Connection)
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public abstract class JDBC {

	protected static final String CONFIG_FILE = "/conexion.properties";
	protected static String url, userName, password;
	protected static String CONSULTAS_FILE;

	protected JDBC() throws BusinessException {
		try {
			inicializar();
			crearConexion();
		} catch (SQLException | IOException e) {
			throw new BusinessException("Existe un error al crear la conexión con la BDD");
		}
	}

	public abstract Connection pedirConexion() throws BusinessException;

	/**
	 * A partir del nombre de la consulta busca en el archivo de propiedades
	 * 'consultas_XXX.properties' seleccionado desde el archivo de propiedades
	 * de configuración de la conexión 'conexion.properties'
	 * 
	 * @param nombre_consulta
	 * @return
	 * @throws BusinessException
	 */
	public String getConsulta(String nombre_consulta) throws BusinessException {
		try {
			return Lector.loadProperty(CONSULTAS_FILE, nombre_consulta);
		} catch (IOException e) {
			throw new BusinessException("No se puede recuperar la consulta");
		}
	}

	/**
	 * Cierra el resultset, el preparedStatement y la Connection
	 * 
	 * @param rs
	 * @param pst
	 * @param c
	 * @throws BusinessException
	 */
	public void close(ResultSet rs, PreparedStatement pst, Connection c) throws BusinessException {
		close(rs, pst);
		close(c);
	}

	/**
	 * Cierra el resultset y el preparedStatement
	 * 
	 * @param rs
	 * @param pst
	 * @throws BusinessException
	 */
	public void close(ResultSet rs, PreparedStatement pst) throws BusinessException {
		close(rs);
		close(pst);
	}

	/**
	 * Cierra el resulset
	 * 
	 * @param rs
	 * @throws BusinessException
	 */
	public void close(ResultSet rs) throws BusinessException {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				throw new BusinessException("No se puede cerrar el ResultSet");
			}
	}

	/**
	 * Cierra el preparedStatement
	 * 
	 * @param pst
	 * @throws BusinessException
	 */
	public void close(PreparedStatement pst) throws BusinessException {
		if (pst != null)
			try {
				pst.close();
			} catch (SQLException e) {
				throw new BusinessException("No se puede cerrar el Statement");
			}
	}

	/**
	 * Cierra la conexion
	 * 
	 * @throws BusinessException
	 */
	public void close(Connection c) throws BusinessException {
		if (c != null)
			try {
				c.close();
			} catch (SQLException e) {
				throw new BusinessException("No se puede cerrar la conexion");
			}
	}

	/**
	 * Inicializa los valores de conexión a partir del archivo de propiedades
	 * 'conexion.properties'
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	protected abstract void crearConexion() throws SQLException, IOException;

	/**
	 * Inicializa los valores de conexión leidos del archivo de propiedades
	 * 'conexion.properties' además también selecciona las consultas según el
	 * driver necesario para la conexión
	 * 
	 * @throws IOException
	 */
	protected void inicializar() throws IOException {
		url = Lector.loadProperty(CONFIG_FILE, "url");
		userName = Lector.loadProperty(CONFIG_FILE, "userName");
		password = Lector.loadProperty(CONFIG_FILE, "password");
		CONSULTAS_FILE = Lector.loadProperty(CONFIG_FILE, "archivo_consultas");
	}
}
