package conf.framework.jdbc.core;

import conf.core.Business;
import conf.framework.jdbc.executor.CommandExecutorFactory;
import conf.framework.jdbc.executor.Executor;

public abstract class BusinessJDBCImpl extends Business {

	protected Executor executor = CommandExecutorFactory.getExecutor();

}