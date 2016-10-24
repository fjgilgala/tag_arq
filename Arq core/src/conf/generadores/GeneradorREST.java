package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

public class GeneradorREST {

	public static void start() throws BusinessException {
		String url = UtilGenerador.getRutaPaquetes()+"rest/";
		Escritor.escritorCarpeta(url);
		String name = "Application.java";
		String body = 
					"package " + UtilGenerador.getRutaPaquetesJava() + "rest;\n\n" 
				+ 	"import java.util.HashSet;\n" 
				+ 	"import java.util.Set;\n\n"
				+ 	"public class Application extends javax.ws.rs.core.Application {\n\n"
				+ 	"\t @Override" + "\n"
				+ 	"\t public Set<Class<?>> getClasses() {\n"
				+ 	"\t\t Set<Class<?>> res = new HashSet<>();\n"
				+ 	"\t\t //añadir aqui las implementacines necesarias \n"
				+ 	"\t\t return res;\n \t}\n}";
		Escritor.escritor(url, name, body);
	}
}
