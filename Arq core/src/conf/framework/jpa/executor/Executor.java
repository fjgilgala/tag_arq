package conf.framework.jpa.executor;

import conf.framework.jpa.executor.Action;
import conf.util.BusinessException;

public interface Executor {

	public Object execute(Action action) throws BusinessException;

}
