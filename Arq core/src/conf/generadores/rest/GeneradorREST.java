package conf.generadores.rest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import conf.generadores.UtilGenerador;
import conf.util.BusinessException;
import conf.util.Escritor;
import conf.util.Lector;

/**
 * GeneradorREST clase encargada de generar las clases e interfaces necesarias
 * para proporcionar servicios web REST
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class GeneradorREST {

	static final String directorioDestino = UtilGenerador.getRutaPaquetes() + "rest/";
	static final String paquete_rest_java = UtilGenerador.getRutaPaquetesJava() + "rest";
	static final List<String> names = new ArrayList<String>(
			Arrays.asList("toString", "notifyAll", "notify", "getClass", "hashCode", "equals", "wait"));

	static String directorioOrigen;
	static String paqueteOrigen;

	/**
	 * Genera las interfaces y clases necesarias para todas las clases del
	 * directorio pasado por parámetro en el directorio definido
	 * por @UtilGenerador.@getRutaPaquetes()/rest/
	 * 
	 * @throws BusinessException
	 */
	public static void start(String directorioOrigen) throws BusinessException {
		GeneradorREST.directorioOrigen = directorioOrigen;
		List<String> archivos_clases = cargarDirectorio(directorioOrigen);
		generarDirectorios(directorioDestino);
		for (String nombre : archivos_clases)
			genera(nombre);
		generarAplicacion(directorioDestino);
	}

	/**
	 * Devuelve el paquete donde se encuentran las implementaciones
	 * 
	 * @return String paquete implementaciones
	 */
	public static String getPaqueteImplementaciones() {
		return directorioDestino + "impl";
	}

	// metodos auxiliares
	private static List<String> cargarDirectorio(String directorio) throws BusinessException {
		return Lector.cargarDirectorio(directorio).stream().map(x -> x.substring(0, x.lastIndexOf(".")))
				.collect(Collectors.toList());
	}

	/**
	 * Genera la interfaz rest y su implementación asociada al nombre de la
	 * clase pasada por parametro
	 * 
	 * @param nombre
	 * @throws BusinessException
	 */
	private static void genera(String nombre) throws BusinessException {
		Interfaz interfaz = generaInterfaceRest(nombre);
		Clase clase = generarImplementacion(nombre, interfaz.getNombre());
		Escritor.escritor(directorioDestino, interfaz.getNombre() + ".java", interfaz.getBody());
		Escritor.escritor(directorioDestino + "/impl/", clase.getNombre() + ".java", clase.getBody());
	}

	private static Interfaz generaInterfaceRest(String nombre) throws BusinessException {
		return new Interfaz(nombre, leeMetodos(nombre), paquete_rest_java);
	}

	private static Clase generarImplementacion(String nombre, String interfaz) throws BusinessException {
		return new Clase(nombre, leeMetodos(nombre), paquete_rest_java, interfaz);
	}

	private static List<Metodo> leeMetodos(String clase) throws BusinessException {
		try {
			List<Metodo> metodos = new ArrayList<Metodo>();
			Class<?> laClase = Class.forName(directorioOrigen.replace("/", ".").replace("src.", "") + clase);
			Method[] methods = laClase.getMethods();
			List<Parametro> parametros = null;
			for (int i = 0; i < methods.length; i++) {
				if (!isGeneralMethod(methods[i].getName())) {
					parametros = new ArrayList<Parametro>();
					for (int j = 0; j < methods[i].getParameters().length; j++)
						parametros.add(new Parametro(methods[i].getParameters()[j].getType().getName(),
								methods[i].getParameters()[j].getName()));
					metodos.add(new Metodo(methods[i].getReturnType().getName(), methods[i].getName(), parametros));
				}
			}
			return metodos;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("No se encuentra la clase");
		}
	}

	private static boolean isGeneralMethod(String nombre) {
		if (names.contains(nombre))
			return true;
		else
			return false;
	}

	private static void generarAplicacion(String directorioDestino) throws BusinessException {
		String name = "Application.java";
		String body = "package " + UtilGenerador.getRutaPaquetesJava() + "rest;\n\n" + "import java.util.Set;\n\n"
				+ "public class Application extends conf.framework.rest.Application {\n" + "\t@Override\n"
				+ "\tpublic Set<Class<?>> getClasses() {\n" + "\t\treturn super.getClasses();\n" + "\t}\n}";
		String url = UtilGenerador.getRutaPaquetes() + "rest/";
		Escritor.escritor(url, name, body);
	}

	private static void generarDirectorios(String directorioDestino) throws BusinessException {
		Escritor.escritorCarpeta(directorioDestino);
		Escritor.escritorCarpeta(directorioDestino + "/impl");
	}

	// fin metodos auxiliares
}
