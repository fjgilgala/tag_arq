package conf.framework.rest;

import java.util.List;

class Metodo {
	
	private String tipoDevuelto;
	private String nombre;
	private List<Parametro> parametros;

	public Metodo(String tipoDevuelto, String nombre, List<Parametro> parametros) {
		this.tipoDevuelto = tipoDevuelto;
		this.nombre = nombre;
		this.parametros = parametros;
	}

	public String generaCabeceraREST(){
		String text = "\t@GET / @POST / @ PUT / @DELETE \n\t@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) / @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})\n";
		String aux = "";
		if (parametros.size() > 0)
			text += "\t@Path(\"";
		for (int i = 0; i<parametros.size(); i++){
			if (i == parametros.size()-1){
				text += "{"+parametros.get(i).getNombre()+"}\")\n";
				aux += "@PathParam(\""+parametros.get(i).getNombre()+"\") "+parametros.get(i);
			} else {
				text += "{"+parametros.get(i).getNombre()+"}, ";	
				aux += "@PathParam(\""+parametros.get(i).getNombre()+"\") "+parametros.get(i)+", ";
			}
		}
		text += "\t"+tipoDevuelto + " " + nombre +"("+aux+") throws BusinessException;\n";
		return text;
	}
	
	public String generaCabeceraInterface(){
		return "\t"+tipoDevuelto + " " + nombre +"("+parametros()+") throws BusinessException;";
	}
	
	public String generaCabeceraClaseImplementada(){	
		return "\t@Override\n\tpublic "+tipoDevuelto + " " + nombre +"("+parametros()+") throws BusinessException {\n \t\t// sin implementar\n \t}\n";
	}
	
	private String parametros(){
		String aux = "";
		for (int i = 0; i<parametros.size(); i++)
			if (i == parametros.size()-1)
				aux += parametros.get(i);
			else
				aux += parametros.get(i)+", ";
		return aux;
	}	
}