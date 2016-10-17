package persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import conf.core.Persistence;
import model.Libro;

public class PersistenceImple extends Persistence {

	public List<Libro> libros = new ArrayList<Libro>(
			Arrays.asList(new Libro("A"), new Libro("B"), new Libro("C")));

	public void guardarLibro(Libro libro) {
		libros.add(libro);
	}

	public void borrarLibro(Libro libro) {
		libros.remove(libro);
	}

	public List<Libro> cargarLibros() {
		return libros;
	}

}
