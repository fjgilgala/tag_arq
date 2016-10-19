package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

public class GeneradorArchivosConexion {

	public static void run(String url, String username, String password) throws BusinessException {
		String text = "url = " + url + "\n" + "userName = " + username + "\n" + "password = " + password + "\n"
				+ "maxStatements = 200\n" + "maxPoolSize = 30\n" + "minPoolSize = 3\n"
				+ "archivo_consultas = /consultas.properties";
		Escritor.escritor("src/", "conexion.properties", text);
		generarArchivoConsulta();
	}

	public static void runExampleHSQLDB() throws BusinessException {
		run("jdbc:hsqldb:hsql://localhost", "userName = sa", "");
	}

	private static void generarArchivoConsulta() throws BusinessException {
		String text = "EJEMPLO = SELECT * FROM EJEMPLO";
		Escritor.escritor("src/", "consultas.properties", text);
	}

}