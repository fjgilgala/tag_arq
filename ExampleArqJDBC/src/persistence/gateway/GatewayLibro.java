package persistence.gateway;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conf.framework.jdbc.core.JDBCFactory;
import conf.framework.jdbc.gateway.Gateway;
import conf.util.BusinessException;
import model.Libro;

/**
 * GatewayLibro es una implementación de la clase @Gateway para los objetos
 * libro del modelo
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class GatewayLibro extends Gateway {

	public void guardarLibro(Libro libro) throws BusinessException {
		try {
			prepararStatement("ADD");
			pst.setInt(1, libro.getId());
			pst.setString(2, libro.getTitulo());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException("Existe un error al ejecutar la consulta de añadir el libro");
		} finally {
			JDBCFactory.getJDBC().close(rs, pst);
		}
	}

	public List<Libro> listadoLibros() throws BusinessException {
		List<Libro> libros = new ArrayList<Libro>();
		try {
			prepararStatement("LISTAR_LIBROS");
			rs = pst.executeQuery();
			while (rs.next())
				libros.add(new Libro(rs.getInt("ID"), rs.getString("TITULO")));

		} catch (SQLException e) {
			throw new BusinessException("Existe un error al ejecutar la consulta de listar libros");
		} finally {
			JDBCFactory.getJDBC().close(rs, pst);
		}
		return libros;
	}

	public void borrarTodo() throws BusinessException {
		try {
			prepararStatement("BORRA_TODO");
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException("Existe un error al ejecutar la consulta de añadir el libro");
		} finally {
			JDBCFactory.getJDBC().close(rs, pst);
		}
	}

}
