package business;

import java.util.List;

import business.actions.AñadirLibro;
import business.actions.BorraTodo;
import business.actions.ListadoLibros;
import conf.core.Business;
import conf.framework.jdbc.executor.CommandExecutorFactory;
import conf.framework.jdbc.executor.Executor;
import conf.util.BusinessException;
import model.Libro;

/**
 * BusinessImpl
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class BusinessImpl extends Business {

	private Executor executor = CommandExecutorFactory.getExecutor();

	public void añadirLibro(Libro libro) throws BusinessException {
		executor.execute(new AñadirLibro(libro));
	}

	@SuppressWarnings("unchecked")
	public List<Libro> listaLibros() throws BusinessException {
		return (List<Libro>) new ListadoLibros().execute();
	}

	public void borraTodo() throws BusinessException {
		new BorraTodo().execute();
	}

}
