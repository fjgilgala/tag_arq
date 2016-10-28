package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

public class GeneradorREST {
	
	public static void generaraAplicacion(String directorioDestino) throws BusinessException{
		String name =   "Application.java";
		String body = 	"package " + UtilGenerador.getRutaPaquetesJava() + "rest;\n\n" +
						"import java.util.Set;\n\n"+
						"public class Application extends conf.framework.rest.Application {\n"+
						"\t@Override\n"+
						"\tpublic Set<Class<?>> getClasses() {\n"+
						"\t\treturn super.getClasses();\n"+
						"\t}\n}";
		String url = UtilGenerador.getRutaPaquetes() + "rest/";
		Escritor.escritor(url, name, body);
	}

	public static void generaDirectorios(String directorioDestino) throws BusinessException{
		Escritor.escritorCarpeta(directorioDestino);
		Escritor.escritorCarpeta(directorioDestino + "/impl");
	}
	
}
