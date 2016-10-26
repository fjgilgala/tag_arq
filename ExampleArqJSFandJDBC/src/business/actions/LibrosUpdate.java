package business.actions;

import conf.core.Service;
import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import model.Libro;
import persistence.LibroDao;
import persistence.PersistenceImpl;

public class LibrosUpdate extends Action {

	protected Libro libro;

	public LibrosUpdate(Libro libro) {
		this.libro = libro;
	}

	@Override
	public Object execute() throws BusinessException {
		LibroDao dao = ((PersistenceImpl) Service.get().persistence()).getGatewayLibro();
		try {
			dao.update(libro);
		} catch (BusinessException ex) {
			throw new BusinessException("Libro no eliminado " + libro, ex);
		}
		return null;
	}
}
