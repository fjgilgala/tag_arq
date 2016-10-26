package persistence;

import java.util.List;

import conf.core.Persistence;
import model.Libro;
import presentation.App;

/**
 * PersistenceImpl es la implementación concreta de la clase Persistence del
 * framework, necesaria para poder iniciarlo
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class PersistenceImpl extends Persistence {

	public void añadirLibro(Libro libro) {
		App.JpaManager().persist(libro);
	}

	@SuppressWarnings("unchecked")
	public List<Libro> listadoLibros() {
		return App.JpaManager().createNamedQuery("Libro.findAll").getResultList();
	}
}