package business.actions;

import conf.framework.jpa.executor.Action;
import conf.util.BusinessException;
import gui.App;
import persistence.PersistenceImpl;

public class ListadoLibros extends Action {

	@Override
	public Object execute() throws BusinessException {
		return ((PersistenceImpl) App.get().persistence()).listadoLibros();
	}
}