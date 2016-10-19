package business.actions;

import conf.framework.jpa.executor.Action;
import conf.util.BusinessException;
import persistence.PersistenceImpl;

public class ListadoLibros extends Action {

	@Override
	public Object execute() throws BusinessException {
		return ((PersistenceImpl) getService().get().persistence()).listadoLibros();
	}

}
