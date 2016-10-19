package conf.generadores;

import java.io.IOException;

import conf.util.BusinessException;
import conf.util.Escritor;
import conf.util.Lector;

public class GeneradorJPAFile {

	public static void start() throws BusinessException {
		try {
			String ruta = "src/META-INF";
			Escritor.escritorCarpeta(ruta);
			Escritor.escritor(ruta, "/persistence.xml", Lector.loadArchive("src/resources/persistenceXMLforJPA"));
			Escritor.escritor(ruta, "/orm.xml", Lector.loadArchive("src/resources/ormXMLforJPA"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("Error al generar los archivos de META-INF");
		}
	}
}