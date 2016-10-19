package persistence;

import conf.core.Persistence;
import persistence.gateway.GatewayLibro;

/**
 * PersistenceImpl es la implementación concreta de la clase Persistence del
 * framework, necesaria para poder iniciarlo
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class PersistenceImpl extends Persistence {

	public GatewayLibro gatewayLibro() {
		return new GatewayLibro();
	}

}
