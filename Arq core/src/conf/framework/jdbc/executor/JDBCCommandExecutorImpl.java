package conf.framework.jdbc.executor;

import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;

public class JDBCCommandExecutorImpl implements Executor {

	public Object execute(Action action) throws BusinessException {
		return action.execute();
	}

}
