package business.actions;

import conf.core.Service;
import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import persistence.LibroDao;
import persistence.PersistenceImpl;

public class LibrosBaja extends Action {

	protected Long id;

	public LibrosBaja(Long id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		LibroDao dao = ((PersistenceImpl) Service.get().persistence()).getGatewayLibro();
		try {
			dao.delete(id);
		} catch (BusinessException ex) {
			throw new BusinessException("Libro no eliminado " + id, ex);
		}
		return null;
	}
}
