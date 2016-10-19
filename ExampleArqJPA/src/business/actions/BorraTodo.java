package business.actions;

import conf.framework.jpa.executor.Action;
import conf.util.BusinessException;
import persistence.PersistenceImpl;

public class BorraTodo extends Action {

	@Override
	public Object execute() throws BusinessException {
		((PersistenceImpl) getService().get().persistence()).borrarTodo();
		return null;
	}

}
