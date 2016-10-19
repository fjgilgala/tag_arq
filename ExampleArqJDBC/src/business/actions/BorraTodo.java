package business.actions;

import conf.core.Service;
import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import persistence.PersistenceImpl;
import persistence.gateway.GatewayLibro;

public class BorraTodo extends Action {

	@Override
	public Object execute() throws BusinessException {
		Service s = new Service();
		establecerConexion();
		GatewayLibro gateway = ((PersistenceImpl) s.get().persistence()).gatewayLibro();
		gateway.setConexion(c);
		gateway.borrarTodo();
		cerrarConexion();
		return null;
	}

}
