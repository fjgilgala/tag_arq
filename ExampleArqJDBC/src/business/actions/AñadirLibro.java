package business.actions;

import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import gui.App;
import model.Libro;
import persistence.PersistenceImpl;
import persistence.gateway.GatewayLibro;

public class AñadirLibro extends Action {

	private Libro libro;

	public AñadirLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public Object execute() throws BusinessException {
		establecerConexion();
		GatewayLibro gateway = ((PersistenceImpl) App.get().persistence()).gatewayLibro();
		gateway.setConexion(c);
		gateway.guardarLibro(libro);
		cerrarConexion();
		return null;
	}

}
