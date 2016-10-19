
package gui;

import java.sql.SQLException;
import java.util.List;

import business.BusinessImple;
import conf.core.Service;
import conf.framework.jdbc.JDBCFactory;
import conf.generadores.GeneradorArchivosConexion;
import conf.gestorpersistance.GestorEmbeddedBD;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImple;

/**
 * Ejemplo básico
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class App {

	public static void main(String[] args) throws BusinessException {

		// Inicia servicios de persistencia
		try {
			GeneradorArchivosConexion.runExampleHSQLDB();
			GestorEmbeddedBD.runDerby();
			JDBCFactory.getJDBC().pedirConexion().createStatement()
					.execute("create table Libros(id INT PRIMARY KEY, titulo varchar(50))");
		} catch (SQLException e) {
		} finally {
		}

		// Inicia el framework
		Service s = new Service();
		s.start(new BusinessImple(), new PersistenceImple());

		// App
		BusinessImple b = (BusinessImple) s.get().business();
		List<Libro> libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);
		b.borraTodo();
		b.añadirLibro(new Libro(1, "telecadas varias"));
		b.añadirLibro(new Libro(2, "patrones de diseño"));
		libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);

	}
}
