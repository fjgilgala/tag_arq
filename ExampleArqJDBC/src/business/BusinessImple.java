package business;

import java.util.List;

import business.actions.AñadirLibro;
import business.actions.BorraTodo;
import business.actions.ListadoLibros;
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

	public void añadirLibro(Libro libro) throws BusinessException {
		new AñadirLibro(libro).execute();
	}

	@SuppressWarnings("unchecked")
	public List<Libro> listaLibros() throws BusinessException {
		return (List<Libro>) new ListadoLibros().execute();
	}

	public void borraTodo() throws BusinessException {
		new BorraTodo().execute();
	}

}
