package business.actions;

import java.util.List;

import conf.core.Service;
import conf.framework.jdbc.Action;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImple;
import persistence.gateway.GatewayLibro;

public class ListadoLibros extends Action {

	@Override
	public Object execute() throws BusinessException {
		Service s = new Service();
		establecerConexion();
		GatewayLibro gateway = ((PersistenceImple) s.get().persistence()).gatewayLibro();
		gateway.setConexion(c);
		List<Libro> l = gateway.listadoLibros();
		cerrarConexion();
		return l;
	}

}
