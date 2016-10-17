package arq.java.ee.conf.jpa;

import arq.java.ee.conf.util.BusinessException;

public interface Executor {

	Object execute(Command command) throws BusinessException;

}
