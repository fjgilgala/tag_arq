package conf.framework.jdbc.executor;

import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import conf.util.LoggerImpl;

public class JDBCCommandExecutorImpl implements Executor {

	public Object execute(Action action) throws BusinessException {
		LoggerImpl.log("Ejecutado command " + action.getClass().getName());
		return action.execute();
	}

}
