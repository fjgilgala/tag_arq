package conf.framework.jpa;

import conf.util.BusinessException;

public interface Action {

	Object execute() throws BusinessException;

}
