package persistence.gateway;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conf.framework.jdbc.core.JDBCFactory;
import conf.framework.jdbc.gateway.Gateway;
import conf.util.BusinessException;
import model.Libro;
import persistence.LibroDao;

public class GatewayLibro extends Gateway implements LibroDao {

	public List<Libro> getLibros() throws BusinessException {
		List<Libro> libros = new ArrayList<Libro>();
		try {
			c = JDBCFactory.getJDBC().pedirConexion();
			prepararStatement("LIST");
			rs = pst.executeQuery();
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setId(rs.getLong("ID"));
				libro.setAutor(rs.getString("AUTOR"));
				libro.setTitulo(rs.getString("TITULO"));
				libros.add(libro);
			}
		} catch (SQLException e) {
			throw new BusinessException("Existe un error al ejecutar la consulta de listado de libros");
		} finally {
			JDBCFactory.getJDBC().close(rs, pst, c);
		}
		return libros;
	}

	public void delete(Long id) throws BusinessException {
		int rows = 0;
		try {
			c = JDBCFactory.getJDBC().pedirConexion();
			prepararStatement("DEL");
			pst.setLong(1, id);
			rows = pst.executeUpdate();
			if (rows != 1)
				throw new BusinessException("Libro " + id + " not found");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Existe un error al ejecutar la consulta de borrado de libro");
		} finally {
			JDBCFactory.getJDBC().close(rs, pst, c);
		}
	}

	public Libro findById(Long id) throws BusinessException {
		Libro libro = null;
		try {
			c = JDBCFactory.getJDBC().pedirConexion();
			prepararStatement("FIND");
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				libro = new Libro();
				libro.setId(rs.getLong("ID"));
				libro.setAutor(rs.getString("AUTOR"));
				libro.setTitulo(rs.getString("TITULO"));
			}
		} catch (SQLException e) {
			throw new BusinessException("Existe un error al ejecutar la consulta de buscar libro");
		} finally {
			JDBCFactory.getJDBC().close(rs, pst, c);
		}
		return libro;
	}

	public void save(Libro l) throws BusinessException {
		try {
			int rows = 0;
			c = JDBCFactory.getJDBC().pedirConexion();
			prepararStatement("ADD");
			pst.setString(1, l.getAutor());
			pst.setString(2, l.getAutor());
			rows = pst.executeUpdate();
			if (rows != 1)
				throw new BusinessException("Libro " + l + " already persisted");
		} catch (SQLException e) {
			throw new BusinessException("Existe un error al ejecutar la consulta de añadir alumno");
		} finally {
			JDBCFactory.getJDBC().close(rs, pst, c);
		}
	}

	public void update(Libro l) throws BusinessException {
		int rows = 0;
		try {
			c = JDBCFactory.getJDBC().pedirConexion();
			prepararStatement("UPDATE");
			pst.setString(1, l.getAutor());
			pst.setString(2, l.getTitulo());
			pst.setLong(3, l.getId());
			rows = pst.executeUpdate();
			if (rows != 1) {
				throw new BusinessException("Libro " + l + " not found");
			}
		} catch (SQLException e) {
			throw new BusinessException("Existe un error al ejecutar la consulta de actualizar libro");
		} finally {
			JDBCFactory.getJDBC().close(rs, pst, c);
		}
	}
}