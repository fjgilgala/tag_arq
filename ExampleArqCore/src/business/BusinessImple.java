package business;

import java.util.List;

import conf.core.Business;
import conf.core.Persistence;
import conf.core.Service;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImple;

public class BusinessImple extends Business {

	private PersistenceImple p;

	@Override
	public Persistence getPersistence() throws BusinessException {
		if (p == null)
			p = (PersistenceImple) new Service().get().persistence();
		return p;
	}

	// se debe refactorizar todo
	public void añadirLibro(String nombre) throws BusinessException {
		((PersistenceImple) getPersistence()).guardarLibro(new Libro(nombre));
	}

	public void borrarLibro(String nombre) throws BusinessException {
		((PersistenceImple) getPersistence()).borrarLibro(new Libro(nombre));
	}

	public List<Libro> listaLibros() throws BusinessException {
		return ((PersistenceImple) getPersistence()).cargarLibros();
	}

}
