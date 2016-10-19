package business.actions;

import conf.core.Service;
import conf.framework.jdbc.Action;
import conf.util.BusinessException;
import persistence.PersistenceImple;
import persistence.gateway.GatewayLibro;

public class BorraTodo extends Action {

	@Override
	public Object execute() throws BusinessException {
		Service s = new Service();
		establecerConexion();
		GatewayLibro gateway = ((PersistenceImple) s.get().persistence()).gatewayLibro();
		gateway.setConexion(c);
		gateway.borrarTodo();
		cerrarConexion();
		return null;
	}

}
