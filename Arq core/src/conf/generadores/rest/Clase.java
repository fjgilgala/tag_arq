package conf.generadores.rest;

import java.util.List;

class Clase {
	
	private String nombre;
	private String paquete;
	private String interfaz;
	private List<Metodo> metodos;

	public Clase(String nombre, List<Metodo> metodos, String paquete, String interfaz) {
		this.nombre = nombre;
		this.metodos = metodos;
		this.paquete = paquete;
		this.interfaz = interfaz;
	}
	
	public String getBody() {
		String body = "package "+paquete+".impl;\n\n"+
				"import "+paquete+"."+interfaz+";\n\n"+
				"// Es necesario que al descomentar añada los imports necesarios al descomentar los métodos generados\n\n"+
				"public class "+getNombre()+" implements "+interfaz+"{\n\n";
		body += "//Modifique las cabeceras de acuerdo a su implementacion";
		body += "\n\n /*";
		body += "\n\n";
		for (Metodo m : metodos)
			body += m.generaCabeceraClaseImplementada()+"\n\n";
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
		return nombre+"RestImp";
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}