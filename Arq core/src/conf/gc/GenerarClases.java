package conf.gc;

import java.io.File;
import java.io.FileWriter;

import conf.util.BusinessException;

/**
 * 
 * GenerarClases encargada de generar las clases demo de la libreria
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class GenerarClases {

	/**
	 * Genera las clases demo BusinessImpl y PersistenceImpl y las guarda en los
	 * paquetes Business y Persistence respectivamente.
	 * 
	 * @throws BusinessException
	 */
	public static void example() throws BusinessException {
		String businessName = "BusinessImpl.java";
		String businessBody = "package business;\nimport conf.core.Business;\n"
				+ "public class BusinessImpl extends Business {\n\n" + "\t //clase autogenerada" + "\n\n}";
		String businessUrl = "src/business/";
		generadorCodigo(businessUrl, businessName, businessBody);

		String persistenceName = "PersistenceImpl.java";
		String persistenceBody = "package persistence;\nimport conf.core.Persistence;\n"
				+ "public class PersistenceImpl extends Persistence {\n\n" + "\t //clase autogenerada" + "\n\n}";
		String persistenceUrl = "src/persistence/";
		generadorCodigo(persistenceUrl, persistenceName, persistenceBody);
		System.out.println("Clases de prueba generadas correctamente");
	}

	private static void generadorCodigo(String url, String name, String body) throws BusinessException {
		try {
			File archivo = new File(url + name);
			if (!archivo.exists()) {
				FileWriter escribir = new FileWriter(archivo, true);
				escribir.write(body);
				escribir.close();
			}
		} catch (Exception e) {
			throw new BusinessException("Error al generar el código para la clase " + name);
		}
	}
}
