package conf.util;

import java.io.File;
import java.io.FileWriter;

/**
 * Clase encargada de generar los archivos necesarios de la app
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class Escritor {

	public static void escritorCarpeta(String url) {
		File directorio = new File(url);
		if (!directorio.exists())
			directorio.mkdirs();
	}

	public static void escritor(String url, String name, String body) throws BusinessException {
		try {
			File archivo = new File(url + name);
			if (!archivo.exists()) {
				FileWriter escribir = new FileWriter(archivo, true);
				escribir.write(body);
				escribir.close();
			}
		} catch (Exception e) {
			throw new BusinessException("Error al escribir el fichero " + name);
		}
	}

	public static void escritorForzoso(String url, String name, String body) throws BusinessException {
		try {
			File archivo = new File(url + name);
			archivo.delete();
			archivo = new File(url + name);
			FileWriter escribir = new FileWriter(archivo, true);
			escribir.write(body);
			escribir.close();
		} catch (Exception e) {
			throw new BusinessException("Error al escribir el fichero " + name);
		}
	}

	public static void writeLine(String line, String fichero) throws BusinessException {
		try {
			File archivo = new File(fichero);
			FileWriter escribir = new FileWriter(archivo, true);
			escribir.write(line);
			escribir.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Error al escribir el fichero " + fichero);
		}
	}
}