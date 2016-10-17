package conf.gc;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * 
 * CompilarClases encargado de compilar las clases del proyecto.
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class CompilarClases {

	/**
	 * Recibe el nombre completo de paquete (example: src.business) y el nombre
	 * de la clase (example: BusinessImpl.java)
	 * 
	 * @param packageName
	 * @param className
	 */
	public static void compilar(String packageName, String className) {
		String fuenteJava = packageName + java.io.File.separator + className;
		JavaCompiler compilador = ToolProvider.getSystemJavaCompiler();
		compilador.run(null, null, null, fuenteJava);
	}

}
