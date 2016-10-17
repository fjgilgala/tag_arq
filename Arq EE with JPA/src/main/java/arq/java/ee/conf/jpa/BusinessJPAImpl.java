package arq.java.ee.conf.jpa;

import arq.java.ee.conf.core.Business;

public abstract class BusinessJPAImpl extends Business{
	
	protected Executor executor = CommandExecutorFactory.getExecutor();
	
}