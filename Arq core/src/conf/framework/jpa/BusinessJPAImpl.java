package conf.framework.jpa;

import conf.core.Business;

public abstract class BusinessJPAImpl extends Business{
	
	protected Executor executor = CommandExecutorFactory.getExecutor();
	
}