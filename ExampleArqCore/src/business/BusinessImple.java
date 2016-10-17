package business;

import java.util.List;

import conf.core.Business;
import conf.core.Service;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImple;

public class BusinessImple extends Business {

	private PersistenceImple p;

	// se debe refactorizar todo
	public void añadirLibro(String nombre) throws BusinessException {
		p = (PersistenceImple) new Service().get().persistence();
		p.guardarLibro(new Libro(nombre));
	}

	public void borrarLibro(String nombre) throws BusinessException {
		p = (PersistenceImple) new Service().get().persistence();
		p.borrarLibro(new Libro(nombre));
	}

	public List<Libro> listaLibros() throws BusinessException {
		p = (PersistenceImple) new Service().get().persistence();
		return p.cargarLibros();
	}

}