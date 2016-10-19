package business.actions;

import conf.core.Service;
import conf.framework.jdbc.Action;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImple;
import persistence.gateway.GatewayLibro;

public class AñadirLibro extends Action {

	private Libro libro;

	public AñadirLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public Object execute() throws BusinessException {
		Service s = new Service();
		establecerConexion();
		GatewayLibro gateway = ((PersistenceImple) s.get().persistence()).gatewayLibro();
		gateway.setConexion(c);
		gateway.guardarLibro(libro);
		cerrarConexion();
		return null;
	}

}
