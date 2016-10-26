package business.actions;

import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import persistence.PersistenceImpl;
import persistence.gateway.GatewayLibro;
import presentation.App;

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
