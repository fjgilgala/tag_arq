package conf.framework.rest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import conf.generadores.GeneradorREST;
import conf.generadores.UtilGenerador;
import conf.util.BusinessException;
import conf.util.Escritor;
import conf.util.Lector;

public class ServiceREST {

	static final String directorioDestino = UtilGenerador.getRutaPaquetes() + "rest/";
	static String paquete_rest_java = UtilGenerador.getRutaPaquetesJava() + "rest";

	static String directorioOrigen;
	static String paqueteOrigen;
	static List<String> clases;

	/**
	 * Genera las interfaces y clases necesarias para todas las clases del
	 * directorio pasado por parámetro
	 * 
	 * @throws BusinessException
	 */
	public static void start(String directorioOrigen) throws BusinessException {
		ServiceREST.directorioOrigen = directorioOrigen;
		clases = new ArrayList<String>();
		List<String> archivos_clases = cargarDirectorio(directorioOrigen);
		GeneradorREST.generaDirectorios(directorioDestino);
		for (String nombre : archivos_clases)
			genera(nombre);
		GeneradorREST.generaraAplicacion(directorioDestino);
	}

	/**
	 * Devuelve una lista con los nombres de las clases generadas
	 * 
	 * @return List<String> clases
	 */
	static List<String> registroClases() {
		return clases;
	}

	/**
	 * Devuelve el paquete donde se encuentran las implementaciones
	 * 
	 * @return String paquete implementaciones
	 */
	static String getPaqueteImplementaciones() {
		return directorioDestino + ".impl";
	}

	// metodos auxiliares
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
		clases.add(clase.getNombre());
	}

	private static List<String> cargarDirectorio(String directorio) throws BusinessException {
		return Lector.cargarDirectorio(directorio).stream().map(x -> x.substring(0, x.lastIndexOf(".")))
				.collect(Collectors.toList());
	}

	private static Interfaz generaInterfaceRest(String nombre) throws BusinessException {
		return new Interfaz(nombre, leeMetodos(nombre), paquete_rest_java);
	}

	private static Clase generarImplementacion(String nombre, String interfaz)
			throws BusinessException {
		return new Clase(nombre, leeMetodos(nombre), paquete_rest_java, interfaz);
	}

	private static List<Metodo> leeMetodos(String clase) throws BusinessException {
		try {
			List<Metodo> metodos = new ArrayList<Metodo>();
			Class<?> laClase = Class.forName("business" + "." + clase);
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
			throw new BusinessException("No se encuentran la clase");
		}
	}

	private static boolean isGeneralMethod(String nombre) {
		ArrayList<String> names = new ArrayList<String>(
				Arrays.asList("toString", "notifyAll", "notify", "getClass", "hashCode", "equals", "wait"));
		if (names.contains(nombre))
			return true;
		else
			return false;
	}
	// fin metodos auxiliares
}
