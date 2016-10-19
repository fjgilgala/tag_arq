package conf.util;

import java.io.File;
import java.io.FileWriter;

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

}
