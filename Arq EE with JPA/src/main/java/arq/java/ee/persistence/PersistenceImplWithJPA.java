package arq.java.ee.persistence;

import arq.java.ee.conf.core.Persistence;
import arq.java.ee.conf.jpa.Jpa;
import arq.java.ee.conf.util.BusinessException;

public class PersistenceImplWithJPA extends Persistence {
	
	public void example(String user) throws BusinessException{
		Jpa.getManager().find(Object.class, user);
	}
}