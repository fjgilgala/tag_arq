
package gui;

import java.sql.SQLException;
import java.util.List;

import business.BusinessImpl;
import conf.core.Service;
import conf.framework.jdbc.core.JDBCFactory;
import conf.generadores.GeneradorArchivosConexion;
import conf.gestorpersistance.GestorEmbeddedBD;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImpl;

/**
 * Ejemplo básico basado en el framework especializado en consultas JDBC sin
 * mapeador JPA
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class App {

	public static void main(String[] args) throws BusinessException {

		// Inicia servicios de persistencia
		try {
			GeneradorArchivosConexion.runExampleHSQLDB();
			GestorEmbeddedBD.runDerby("internal");
			JDBCFactory.getJDBC().pedirConexion().createStatement()
					.execute("create table Libros(id INT PRIMARY KEY, titulo varchar(50))");
		} catch (SQLException e) {
		} finally {
		}

		// Inicia el framework
		Service s = new Service();
		s.start(new BusinessImpl(), new PersistenceImpl());

		// App
		BusinessImpl b = (BusinessImpl) s.get().business();
		List<Libro> libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);
		b.borraTodo();
		b.añadirLibro(new Libro(1, "telecadas varias"));
		b.añadirLibro(new Libro(2, "patrones de diseño"));
		libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);

		// necesario forzar la detención o finalizar la ejecución; con derby no
		// es necesario
	}
}