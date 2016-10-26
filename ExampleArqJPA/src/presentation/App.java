
package presentation;

import java.util.List;

import business.BusinessImpl;
import conf.core.Service;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImpl;

/**
 * Ejemplo básico basado en el framework especializado en consultas JDBC con
 * mapeador JPA
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class App extends Service {

	public static void main(String[] args) throws BusinessException {

		// Inicia servicios de persistencia; no es necesario para iniciar el
		// framework
		String databasename = "internal";
		generateHSQLServerJPA(databasename);

		// Inicia el framework
		start(new BusinessImpl(), new PersistenceImpl());

		// App
		BusinessImpl b = (BusinessImpl) get().business();
		List<Libro> libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);
		b.añadirLibro(new Libro("telecadas varias"));
		b.añadirLibro(new Libro("patrones de diseño"));
		libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);

		// necesario forzar la detención o finalizar la ejecución
		stopHSQLServer();
	}
}


