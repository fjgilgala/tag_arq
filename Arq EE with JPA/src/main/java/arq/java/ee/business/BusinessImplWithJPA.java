package arq.java.ee.business;

import arq.java.ee.business.command.ExampleCommand;
import arq.java.ee.conf.core.Service;
import arq.java.ee.conf.jpa.BusinessJPAImpl;
import arq.java.ee.conf.util.BusinessException;
import arq.java.ee.persistence.PersistenceImplWithJPA;

public class BusinessImplWithJPA extends BusinessJPAImpl{

	
	protected PersistenceImplWithJPA persistence = (PersistenceImplWithJPA) Service.persistence;
	
	public void example() throws BusinessException{
		executor.execute(new ExampleCommand());
	}
	
}