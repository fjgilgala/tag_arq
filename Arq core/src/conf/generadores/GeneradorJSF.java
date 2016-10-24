package conf.generadores;

import conf.util.BusinessException;
import conf.util.Escritor;

public class GeneradorJSF {
	
	private static String url = "WebContent/WEB-INF/";


	public static void start() throws BusinessException {
		core();
		String webBody = webBody() + "</web-app>";
		Escritor.escritor(url, "web.xml", webBody);
	}
	
	public static void startWithRest() throws BusinessException {
		core();
		String webBody = webBody() + withRest()+ "</web-app>";
		Escritor.escritor(url, "web.xml", webBody);
		GeneradorREST.start();
	}
	
	private static void core() throws BusinessException{
		Escritor.escritorCarpeta("WebContent");
		Escritor.escritorCarpeta("WebContent/templates");
		Escritor.escritorCarpeta("WebContent/WEB-INF");
		Escritor.escritorCarpeta("WebContent/WEB-INF/lib");
		Escritor.escritorCarpeta(UtilGenerador.getRutaPaquetes()+"/presentation");
		// reportador();
		String beansBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
						   "<beans xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"+
						   "xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee\n"+
						   "http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd\"\n"+
						   "version=\"1.1\" bean-discovery-mode=\"none\">\n"+
						   "</beans>";
		Escritor.escritor(url, "beans.xml", beansBody);
		String faces_configBody= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
								 "<faces-config xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\"\n"+
								 "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"+
								 "xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd\"\n"+
								 "version=\"2.2\">\n"+
								 "<application>\n"+
								 "<resource-bundle>\n"+
								 "<base-name>messages</base-name>\n"+
								 "<var>msgs</var>\n"+
								 "</resource-bundle>\n"+
								 "</application>\n"+
								 "</faces-config>";
		Escritor.escritorForzoso(url, "faces-config.xml", faces_configBody);
	}
	
	/*
	private static void reportador() throws BusinessException{
		String url = UtilGenerador.getRutaPaquetes()+"presentation/";
		Escritor.escritorCarpeta(url);
		String name = "Reportador.java";
		String body = 
					"package " + UtilGenerador.getRutaPaquetesJava() + "presentation;\n\n" 
				+ 	"import java.util.ResourceBundle;\n" 
				+ 	"import javax.faces.context.FacesContext;\n\n"
				+ 	"public class Reportador {\n\n"
				+ 	"\t public String error(String error) {\n"
				+ 	"\t\t FacesContext facesContext = FacesContext.getCurrentInstance();\n"
				+ 	"\t\t ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, \"msgs\"); \n"
				+ 	"\t\t return bundle.getString(error);\n \t}\n}";
		Escritor.escritor(url, name, body);
	}
	*/

	private static String webBody() {
		return 	 "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
				 "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" "
				 + "xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee "
				 + "http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd\" id=\"WebApp_ID\" version=\"3.0\">\n"+
				 "<display-name>Example</display-name>\n"+
				 "<welcome-file-list>\n"+
				 "<welcome-file>index.xhtml</welcome-file>\n"+
				 "<welcome-file>index.htm</welcome-file>\n"+
				 "</welcome-file-list>\n"+
				 "<servlet>\n"+
				 "<servlet-name>Faces Servlet</servlet-name>\n"+
				 "<servlet-class>javax.faces.webapp.FacesServlet</servlet-class>\n"+
				 "<load-on-startup>1</load-on-startup>\n"+
				 "</servlet>\n"+
				 "<servlet-mapping>\n"+
				 "<servlet-name>Faces Servlet</servlet-name>\n"+
				 "<url-pattern>*.xhtml</url-pattern>\n"+
				 "</servlet-mapping>\n"+
				 "<context-param>\n"+
				 "<param-name>BootsFaces_USETHEME</param-name>\n"+
				 "<param-value>true</param-value>\n"+
				 "</context-param>\n";
	}
	
	private static String withRest(){
		return  "<context-param>\n"+
				 "<param-name>resteasy.servlet.mapping.prefix</param-name>\n"+
				 "<param-value>/rest</param-value>\n"+
				 "</context-param>\n"+
				 "<servlet>\n"+
				 "<servlet-name>resteasy</servlet-name>\n"+
				 "<servlet-class>\n"+
				 "org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher\n"+
				 "</servlet-class>\n"+
				 "<init-param>\n"+
				 "<param-name>javax.ws.rs.Application</param-name>\n"+
				 "<param-value>"+UtilGenerador.getRutaPaquetesJava()+"rest.Application"+"</param-value>\n"+
				 "</init-param>\n"+
				 "<load-on-startup>1</load-on-startup>\n"+
				 "</servlet>\n"+
				 "<servlet-mapping>\n"+
				 "<servlet-name>resteasy</servlet-name>\n"+
				 "<url-pattern>/rest/*</url-pattern>\n"+
				 "</servlet-mapping>\n"+
				 "<servlet>\n"+
				 "<servlet-name>resteasy-jsapi</servlet-name>\n"+
				 "<servlet-class>org.jboss.resteasy.jsapi.JSAPIServlet</servlet-class>\n"+
				 "<load-on-startup>2</load-on-startup>\n"+
				 "</servlet>\n"+
				 "<servlet-mapping>\n"+
				 "<servlet-name>resteasy-jsapi</servlet-name>\n"+
				 "<url-pattern>/rest-jsapi</url-pattern>\n"+
				 "</servlet-mapping>\n";
	}
	
	/*
	private static void generalPage(){
		String body = 	"<?xml version='1.0' encoding='UTF-8' ?>"+
						"<!DOCTYPE html>"+
						"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:h=\"http://java.sun.com/jsf/html\" "
						+ "xmlns:f=\"http://java.sun.com/jsf/core\" xmlns:ui=\"http://java.sun.com/jsf/facelets\">\n"+
						"<h:head><title>#{msgs.tituloHead}</h:head>\n"+		
						"<h:body style=\"padding-top: 50px; padding-bottom: 20px;\">\n"+
						"<h:form id=\"form-cabecera\"><center><h1>#{msgs.tituloAplicacion1}</h1></center></h:form>\n"+
						"<b:container>\n"+
						"<b:row><ui:insert name=\"cabecera\"></ui:insert></b:row>\n"+
						"<b:row><ui:insert name=\"navegacion\"></ui:insert></b:row>\n"+
						"<b:row><ui:insert name=\"cuerpo\">#{msgs.tituloDefectoCuerpo}</ui:insert></b:row>\n"+
						"<footer><p>© Francisco Javier Gil Gala</p></footer>"+
						"</b:container>"+
						"</h:body>\n"+
						"</f:view>\n"+
						"</html>";
		
	}
	
	private static void indexPage(){
		String body = 	"<?xml version='1.0' encoding='UTF-8' ?>"+
						"<!DOCTYPE html>"+
						"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:h=\"http://java.sun.com/jsf/html\" xmlns:f=\"http://java.sun.com/jsf/core\">\n"+
						"<h:head></h:head>\n"+
						"<h:body>"+
						"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"\n"+
						"xmlns:h=\"http://java.sun.com/jsf/html\"\n"+
						"xmlns:f=\"http://java.sun.com/jsf/core\\n"+
						"xmlns:b=\"http://bootsfaces.net/ui\"\n"+
						"xmlns:ui=\"http://java.sun.com/jsf/facelets\"\n"+
						"template=\"/templates/template-general.xhtml\">\n"+
						"<ui:define name=\"titulo\">\n"+
						"<ui:define name=\"navegacion\"></ui:define>\n"+
						"<ui:define name=\"cuerpo\"></ui:define>\n"+
						"</ui:composition>\n"+
						"</h:body>\n"+
						"</html>";
	}
	*/
}