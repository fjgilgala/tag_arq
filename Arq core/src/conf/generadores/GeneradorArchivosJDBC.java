package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

public class GeneradorArchivosJDBC {

	public static void start(String url, String username, String password) throws BusinessException {
		String text = "url = " + url + "\n" + "userName = " + username + "\n" + "password = " + password + "\n"
				+ "maxStatements = 200\n" + "maxPoolSize = 30\n" + "minPoolSize = 3\n"
				+ "archivo_consultas = /consultas.properties";
		Escritor.escritorForzoso("src/", "conexion.properties", text);
		// generarArchivoConsulta();
	}

	/*
	private static void generarArchivoConsulta() throws BusinessException {
		String text = "LISTAR_LIBROS = select id, titulo from libros\n"
				+ "ADD = insert into libros(id, titulo) values (?, ?)\n" + "BORRA_TODO = truncate table libros";
		Escritor.escritor("src/", "consultas.properties", text);
	}
	*/

}