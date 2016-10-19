package persistence;

import conf.core.Persistence;
import persistence.gateway.GatewayLibro;

public class PersistenceImple extends Persistence {

	public GatewayLibro gatewayLibro() {
		return new GatewayLibro();
	}

}
