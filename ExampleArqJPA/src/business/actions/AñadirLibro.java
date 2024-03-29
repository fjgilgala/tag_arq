package business.actions;

import conf.framework.jpa.executor.Action;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImpl;
import presentation.App;

public class AñadirLibro extends Action {

	private Libro libro;

	public AñadirLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public Object execute() throws BusinessException {
		((PersistenceImpl) App.get().persistence()).añadirLibro(libro);
		return null;
	}
}