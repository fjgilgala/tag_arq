package business;

import java.util.List;

import business.actions.AñadirLibro;
import business.actions.BorraTodo;
import business.actions.ListadoLibros;
import conf.framework.jdbc.core.BusinessJDBCImpl;
import conf.util.BusinessException;
import model.Libro;

/**
 * BusinessImpl
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class BusinessImpl extends BusinessJDBCImpl {

	public void añadirLibro(Libro libro) throws BusinessException {
		executor.execute(new AñadirLibro(libro));
	}

	@SuppressWarnings("unchecked")
	public List<Libro> listaLibros() throws BusinessException {
		return (List<Libro>) executor.execute(new ListadoLibros());
	}

	public void borraTodo() throws BusinessException {
		executor.execute(new BorraTodo());
	}
}