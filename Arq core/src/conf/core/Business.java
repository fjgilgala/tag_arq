package conf.core;

/**
 * 
 * Business clase abstracta de la que se debe extender la funcionalidad para
 * suministrar con ella los servicios de la capa de negocio al resto de capas.
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public abstract class Business {

	protected Business() {
		Factory._setImpleBusiness(this);
	}

	/**
	 * Método para comprobar el correcto funcionamiento del framework
	 */
	public void testComunicaPresentacion() {
		System.out.println("OK: comunicacion persistencia->business");
	}

}
