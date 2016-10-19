package conf.framework;

import conf.util.BusinessException;

public interface Action {

	/**
	 * Implementa la operación principal de la clase que suministra el servicio
	 * 
	 * @throws BusinessException
	 */
	public Object execute() throws BusinessException;

}
