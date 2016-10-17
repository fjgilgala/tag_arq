package arq.java.ee.business.command;

import arq.java.ee.conf.jpa.Command;
import arq.java.ee.conf.util.BusinessException;

public class ExampleCommand implements Command {

	@Override
	public Object execute() throws BusinessException {
		System.out.println("Ejemplo de implementación JPA");
		return null;
	}

}
