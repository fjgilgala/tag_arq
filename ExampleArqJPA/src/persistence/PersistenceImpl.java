package persistence;

import java.util.List;

import conf.core.Persistence;
import conf.framework.jpa.core.Jpa;
import model.Libro;

/**
 * PersistenceImpl es la implementación concreta de la clase Persistence del
 * framework, necesaria para poder iniciarlo
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class PersistenceImpl extends Persistence {

	public void añadirLibro(Libro libro) {
		Jpa.getManager().persist(libro);
	}

	public void borrarTodo() {

	}

	@SuppressWarnings("unchecked")
	public List<Libro> listadoLibros() {
		return Jpa.getManager().createNamedQuery("Libro.findAll").getResultList();
	}
}
