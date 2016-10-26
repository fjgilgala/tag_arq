package business.actions;

import conf.core.Service;
import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import persistence.LibroDao;
import persistence.PersistenceImpl;

public class LibrosListado extends Action {

	@Override
	public Object execute() throws BusinessException {
		LibroDao dao = ((PersistenceImpl) Service.get().persistence()).getGatewayLibro();
		return dao.getLibros();
	}
}
