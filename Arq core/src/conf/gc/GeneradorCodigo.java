package conf.gc;

import java.io.File;
import java.io.FileWriter;

import conf.util.BusinessException;

/**
 * 
 * GenerarCodigo encargada de generar las clases y paquetes demo
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class GeneradorCodigo {

	/**
	 * Genera las clases demo BusinessImpl y PersistenceImpl y las guarda en los
	 * paquetes Business y Persistence respectivamente.
	 * 
	 * @throws BusinessException
	 */
	public static void generarDemo() throws BusinessException {
		String businessName = "BusinessImpl.java";
		String businessBody = 
				"package business;\n\n"
				+ "import conf.core.Business;\n"
				+ "import conf.core.Persistence;\n"
				+ "import conf.core.Service;\n"
				+ "import conf.util.BusinessException;\n"
				+ "import persistence.PersistenceImpl;\n\n"
				+ "public class BusinessImpl extends Business {\n\n"
				+ "\t //clase autogenerada" + "\n"
				+ "\t private PersistenceImpl p;\n\n"
				+ "\t @Override\n"
				+ "\t public Persistence getPersistence() throws BusinessException {\n"
				+ "\t\tif (p == null)\n"
				+ "\t\t\t p = (PersistenceImpl) new Service().get().persistence();\n"
				+ "\t\t return p;\n"
				+ "\t}\n}";
		String businessUrl = "src/business/";
		generadorPaquetes(businessUrl);
		generadorCodigo(businessUrl, businessName, businessBody);

		String persistenceName = "PersistenceImpl.java";
		String persistenceBody = 
				"package persistence;\n\n"
				+ "import conf.core.Persistence;\n\n"
				+ "public class PersistenceImpl extends Persistence {\n\n"
				+ "\t //clase autogenerada"
				+ "\n\n}";
		String persistenceUrl = "src/persistence/";
		generadorPaquetes(persistenceUrl);
		generadorCodigo(persistenceUrl, persistenceName, persistenceBody);
		System.out.println("Clases de prueba generadas correctamente");
	}

	/**
	 * Genera la estructura clásica de paquetes: src.business, src.persistence,
	 * src.model, src.gui
	 */
	public static void generarEstructuraPaquetes() {
		generadorPaquetes("src/business");
		generadorPaquetes("src/persistence");
		generadorPaquetes("src/model");
		generadorPaquetes("src/gui");
	}

	private static void generadorPaquetes(String url) {
		File directorio = new File(url);
		if (!directorio.exists()) {
			System.out.println("-> Directorio no existe, generando directorio " + url);
			directorio.mkdirs();
		}
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
