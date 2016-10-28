package conf.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import conf.core.Service;

/**
 * Clase encargada de leer desde ficheros de propidades
 * 
 * @author Francisco J Gil Gala
 *
 */
public class Lector {

	public static Object createClass(String file, String factoryType) throws ClassNotFoundException, BusinessException,
			InstantiationException, IllegalAccessException, IOException {
		String className = loadProperty(file, factoryType);
		Class<?> clazz = Class.forName(className);
		return clazz.newInstance();
	}

	public static String loadProperty(String file, String property) throws IOException {
		Properties p = loadPropertiesFile(file);
		String value = p.getProperty(property);
		return value;
	}

	public static Properties loadPropertiesFile(String file) throws IOException {
		Properties p = new Properties();
		InputStream is = Service.class.getResourceAsStream(file);
		p.load(is);
		is.close();
		return p;
	}

	@SuppressWarnings("resource")
	public static String loadArchive(String archive) throws BusinessException {
		try {
			String text = "";
			File fichero = new File(archive);
			Scanner s = null;
			s = new Scanner(fichero);
			while (s.hasNextLine())
				text += s.nextLine();
			return text;
		} catch (FileNotFoundException e) {
			throw new BusinessException("El archivo " + archive + " no existe");
		}
	}

	public static List<String> cargarDirectorio(String directorio) throws BusinessException {
		File f = new File(directorio);
		if (f.exists()) {
			List<String> archivos = new ArrayList<String>();
			File[] ficheros = f.listFiles();
			for (int i = 0; i < ficheros.length; i++)
				archivos.add(ficheros[i].getName());
			return archivos;
		} else {
			throw new BusinessException("El directorio " + directorio + " no existe");
		}
	}
}