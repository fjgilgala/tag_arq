package conf.core;

import conf.util.BusinessException;

/**
 * 
 * Business clase abstracta de la que se debe extender la funcionalidad para
 * suministrar con ella los servicios de la capa de negocio al resto de capas.
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public abstract class Business {

	public Business() {
		Factory._setImpleBusiness(this);
	}

	public abstract Persistence getPersistence() throws BusinessException;

	/**
	 * Método para comprobar el correcto funcionamiento del framework
	 */
	public void testComunicaPresentacion() {
		System.out.println("OK: comunicacion persistencia->business");
	}

}
