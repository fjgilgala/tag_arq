package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

public class GeneradorArchivosJPA {

	public static void startHSQLDB(String url, String user, String password) throws BusinessException {
		start("org.hibernate.dialect.HSQLDialect", "org.hsqldb.jdbcDriver", url, user, password);
	}

	private static void start(String dialect, String driver, String url, String user, String password)
			throws BusinessException {
		String ruta = "src/META-INF";
		Escritor.escritorCarpeta(ruta);
		Escritor.escritor(ruta, "/persistence.xml",
				getPersistence(dialect, driver, url, user, password));
		Escritor.escritor(ruta, "/orm.xml", getORM());
	}

	private static String getPersistence(String dialect, String driver, String url, String user, String password) {
		return "<persistence xmlns=\"http://java.sun.com/xml/ns/persistence\""
				+ "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
				+ "\txsi:schemaLocation=\"http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd\"\n"
				+ "\tversion=\"2.0\">\n" + "\t<persistence-unit name=\"exampleJPA\">\n" + "\t\t<properties>\n"
				+ "\t\t\t<property name=\"hibernate.dialect\" value=\"" + dialect + "\" />\n"
				+ "\t\t\t<property name=\"hibernate.hbm2ddl.auto\" value=\"create\" />\n"
				+ "\t\t\t<property name=\"javax.persistence.jdbc.driver\" value=\"" + driver + "\" />\n"
				+ "\t\t\t<property name=\"javax.persistence.jdbc.url\" value=\"" + url + "\" />\n"
				+ "\t\t\t<property name=\"javax.persistence.jdbc.user\" value=\"" + user + "\" />\n"
				+ "\t\t\t<property name=\"javax.persistence.jdbc.password\" value=\"" + password + "\" />\n"
				+ "\t\t</properties>" + "\t</persistence-unit>\n" + "</persistence>";
	}

	private static String getORM() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<entity-mappings xmlns=\"http://java.sun.com/xml/ns/persistence/orm\"\n"
				+ "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
				+ "\txsi:schemaLocation=\"http://java.sun.com/xml/ns/persistence/orm http://java.sun.com/xml/ns/persistence/orm_2_0.xsd\"\n"
				+ "\tversion=\"2.0\">\n" + "\t<named-query name=\"Libro.findAll\">\n"
				+ "\t\t<query>select l from Libro l</query>\n" + "\t</named-query>\n" + "</entity-mappings>\n";
	}

}