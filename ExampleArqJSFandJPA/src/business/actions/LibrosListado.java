package business.actions;

import conf.core.Service;
import conf.framework.jpa.executor.Action;
import conf.util.BusinessException;
import persistence.LibroDao;
import persistence.PersistenceImpl;

public class LibrosListado extends Action {

	@Override
	public Object execute() throws BusinessException {
		LibroDao dao = ((PersistenceImpl) Service.get().persistence());
		return dao.getLibros();
	}
}
