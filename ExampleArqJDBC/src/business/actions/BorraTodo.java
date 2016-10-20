package business.actions;

import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import gui.App;
import persistence.PersistenceImpl;
import persistence.gateway.GatewayLibro;

public class BorraTodo extends Action {

	@Override
	public Object execute() throws BusinessException {
		establecerConexion();
		GatewayLibro gateway = ((PersistenceImpl) App.get().persistence()).gatewayLibro();
		gateway.setConexion(c);
		gateway.borrarTodo();
		cerrarConexion();
		return null;
	}

}
