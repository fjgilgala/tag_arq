package conf.framework.jpa.core;

import conf.core.Business;
import conf.framework.jpa.executor.CommandExecutorFactory;
import conf.framework.jpa.executor.Executor;

public abstract class BusinessJPAImpl extends Business{
	
	protected Executor executor = CommandExecutorFactory.getExecutor();
	
}