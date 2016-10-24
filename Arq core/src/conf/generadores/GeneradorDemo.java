package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

public class GeneradorDemo {
	
	/**
	 * Genera las clases demo JPA BusinessImpl y PersistenceImpl y las guarda en
	 * los paquetes ..\Business y ..\Persistence respectivamente.
	 * 
	 * @throws BusinessException
	 */
	public static void generarDemoJPA() throws BusinessException {
		generarDemo("JPA", "jpa");
	}

	/**
	 * Genera las clases demo JDBC BusinessImpl y PersistenceImpl y las guarda
	 * en los paquetes ..\Business y ..\Persistence respectivamente.
	 * 
	 * @throws BusinessException
	 */
	public static void generarDemoJDBC() throws BusinessException {
		generarDemo("JDBC", "jdbc");
	}

	private static void generarDemo(String framework, String paquete) throws BusinessException {
		String businessName = "BusinessImpl.java";
		String businessBody = 
					"package " + UtilGenerador.getRutaPaquetesJava() + "business;\n\n" 
				+ 	"import conf.core.Persistence;\n" 
				+ 	"import conf.core.Service;\n"
				+ 	"import conf.framework."+paquete+".core.Business"+framework+"Impl;\n"
				+ 	"import conf.util.BusinessException;\n"
				+ 	"public class BusinessImpl extends Business"+framework+"Impl {\n\n"
				+ 	"\t //clase autogenerada" + "\n}";
		String businessUrl = UtilGenerador.getRutaPaquetes() + "business/";
		Escritor.escritorCarpeta(businessUrl);
		Escritor.escritor(businessUrl, businessName, businessBody);
		String persistenceName = "PersistenceImpl.java";
		String persistenceBody = 
					"package " + UtilGenerador.getRutaPaquetesJava() + "persistence;\n\n"
				+ 	"import conf.core.Persistence;\n" 
				+ 	"public class PersistenceImpl extends Persistence {\n\n"
				+ 	"\t //clase autogenerada" + "\n}";
		String persistenceUrl = UtilGenerador.getRutaPaquetes()+ "persistence/";
		Escritor.escritorCarpeta(persistenceUrl);
		Escritor.escritor(persistenceUrl, persistenceName, persistenceBody);
	}


}
