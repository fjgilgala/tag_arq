package conf.framework.jdbc.executor;

import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;

public interface Executor {

	public Object execute(Action action) throws BusinessException;

}
