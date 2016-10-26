package persistence;

import conf.core.Persistence;
import persistence.gateway.GatewayLibro;

public class PersistenceImpl extends Persistence {

	public LibroDao getGatewayLibro() {
		return new GatewayLibro();
	}

}
