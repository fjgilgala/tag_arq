package business.actions;

import java.util.List;

import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImpl;
import persistence.gateway.GatewayLibro;
import presentation.App;

public class ListadoLibros extends Action {

	@Override
	public Object execute() throws BusinessException {
		establecerConexion();
		GatewayLibro gateway = ((PersistenceImpl) App.get().persistence()).gatewayLibro();
		gateway.setConexion(c);
		List<Libro> l = gateway.listadoLibros();
		cerrarConexion();
		return l;
	}

}
