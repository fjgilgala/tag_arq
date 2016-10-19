package conf.framework.jpa;

import conf.util.BusinessException;

public interface Executor {

	Object execute(Action action) throws BusinessException;

}
