package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

/**
 * 
 * GenerarCodigo encargada de generar las clases y paquetes demo
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class GeneradorCodigo {

	private static String ruta = "";

	/**
	 * Genera las clases demo JPA BusinessImpl y PersistenceImpl y las guarda en los
	 * paquetes ..\Business y ..\Persistence respectivamente. 
	 * 
	 * @throws BusinessException
	 */
	public static void generarDemoJPA() throws BusinessException {
		generarDemo("JPA", "jpa");
	}
	
	/**
	 * Genera las clases demo JDBC BusinessImpl y PersistenceImpl y las guarda en los
	 * paquetes ..\Business y ..\Persistence respectivamente. 
	 * 
	 * @throws BusinessException
	 */
	public static void generarDemoJDBC() throws BusinessException {
		generarDemo("JDBC", "jdbc");
	}
	
	private static void generarDemo(String framework, String paquete) throws BusinessException {
		String businessName = "BusinessImpl.java";
		String businessBody = 
					"package " + getRutaPaquetesJava() + "business;\n\n" 
				+ 	"import conf.core.Persistence;\n" 
				+ 	"import conf.core.Service;\n"
				+ 	"import conf.framework."+paquete+".core.Business"+framework+"Impl;\n"
				+ 	"import conf.util.BusinessException;\n"
				+ 	"public class BusinessImpl extends Business"+framework+"Impl {\n\n"
				+ 	"\t //clase autogenerada" + "\n}";
		String businessUrl = "src/" + ruta + "business/";
		Escritor.escritorCarpeta(businessUrl);
		Escritor.escritor(businessUrl, businessName, businessBody);
		String persistenceName = "PersistenceImpl.java";
		String persistenceBody = 
					"package " + getRutaPaquetesJava() + "persistence;\n\n"
				+ 	"import conf.core.Persistence;\n" 
				+ 	"public class PersistenceImpl extends Persistence {\n\n"
				+ 	"\t //clase autogenerada" + "\n}";
		String persistenceUrl = "src/" + ruta + "persistence/";
		Escritor.escritorCarpeta(persistenceUrl);
		Escritor.escritor(persistenceUrl, persistenceName, persistenceBody);
	}

	/**
	 * Modifica la ruta por defecto en la que se generan los paquetes y las
	 * clases. No es necesario indicar /src/.. unicamente los paquetes por
	 * debajo de /src/ p.e: teleco/espartano/ la ruta real será
	 * src/teleco/espartano/
	 * 
	 * @param String
	 *            ruta
	 */
	public static void modificarRutaPaquetes(String ruta) {
		GeneradorCodigo.ruta = ruta;
	}

	/**
	 * Asigna la ruta de paquetes según la establecida en el descriptor de
	 * paquetes de Maven (pom.xml)
	 */
	public static void asignarRutaPaquetesParaMaven() {

	}

	/**
	 * Devuelve la ruta establecida para generar los paquetes y las clases
	 * 
	 * @return String ruta
	 */
	public static String getRutaPaquetes() {
		return "src/" + ruta;
	}

	/**
	 * Devuelve la ruta de paquetes en formato interno de Java p.e:
	 * com.business.
	 * 
	 * @return String ruta
	 */
	public static String getRutaPaquetesJava() {
		String s = ruta.replace("/", ".");
		return s;
	}

	/**
	 * Genera la estructura clásica de paquetes: src.ruta.business,
	 * src.ruta.persistence, src.ruta.model, src.ruta.gui siendo ruta la ruta
	 * predefinida o una ruta asignada a través
	 * de @GeneradorCodigo.modificarRutaPaquetes
	 */
	public static void generarEstructuraPaquetes() {
		Escritor.escritorCarpeta("src/" + ruta + "business");
		Escritor.escritorCarpeta("src/" + ruta + "persistence");
		Escritor.escritorCarpeta("src/" + ruta + "model");
		Escritor.escritorCarpeta("src/" + ruta + "gui");
		Escritor.escritorCarpeta("test/conf");
	}
}