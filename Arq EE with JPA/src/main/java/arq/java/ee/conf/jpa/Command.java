package arq.java.ee.conf.jpa;

import arq.java.ee.conf.util.BusinessException;

public interface Command {

	Object execute() throws BusinessException;

}
