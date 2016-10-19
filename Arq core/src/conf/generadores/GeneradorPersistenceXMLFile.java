package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

public class GeneradorPersistenceXMLFile {

	public static void start() throws BusinessException {
		String text = "";
		Escritor.escritorCarpeta("META-INF");
		Escritor.escritor("META-INF/", "", text);
	}
}