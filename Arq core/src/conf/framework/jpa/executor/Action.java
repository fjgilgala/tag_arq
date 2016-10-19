package conf.framework.jpa.executor;

import conf.core.Service;
import conf.util.BusinessException;

public abstract class Action implements conf.framework.Action {

	public abstract Object execute() throws BusinessException;

	public Service getService() {
		return new Service();
	}

}
