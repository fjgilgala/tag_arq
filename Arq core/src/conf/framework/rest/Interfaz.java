package conf.framework.rest;

import java.util.List;

class Interfaz {

	private String nombre;
	private String paquete;
	private List<Metodo> metodos;

	public Interfaz(String nombre, List<Metodo> metodos, String paquete) {
		this.nombre = nombre;
		this.metodos = metodos;
		this.paquete = paquete;
	}

	public String getBody() {
		String body = "package "+paquete+";\n\n"+
				"/* \n\n"+
				"import javax.ws.rs.*;\n"+
				"import javax.ejb.Stateless;\n"+
				"import javax.ws.rs.core.MediaType;\n\n"+
				"// Es necesario que al descomentar añada los imports necesarios necesarios al descomentar los métodos generados\n\n"+
				"@Stateless\n"+
				"@Path(\"/"+nombre+"Rs"+"\")\n"+
				"*/ \n"+
				"public interface "+getNombre()+" {\n\n";
		body += "//Modifique las cabeceras de acuerdo a su implementacion";
		body += "\n\n /*";
		body += "\n\n";
		for (Metodo m : metodos)
			body += m.generaCabeceraREST()+"\n\n";
		body += "*/ \n}";
		return body;
	}

	public List<Metodo> getMetodos() {
		return metodos;
	}

	public void setMetodos(List<Metodo> metodos) {
		this.metodos = metodos;
	}

	public String getNombre() {
		return nombre+"Rest";
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}