package business.actions;

import conf.core.Service;
import conf.framework.jpa.executor.Action;
import conf.util.BusinessException;
import model.Libro;
import persistence.LibroDao;
import persistence.PersistenceImpl;

public class LibrosBuscar extends Action {

	protected Long id;

	public LibrosBuscar(Long id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		LibroDao dao = ((PersistenceImpl) Service.get().persistence());
		Libro a = dao.findById(id);
		if (a == null) {
			throw new BusinessException("No se ha encontrado el libro");
		}
		return a;
	}
}
