package conf.framework.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Application extends javax.ws.rs.core.Application {

	protected Set<Class<?>> res;

	@Override
	public Set<Class<?>> getClasses() {
		if (res == null) {
			res = new HashSet<>();
			List<String> clases = ServiceREST.registroClases();
			for (String s : clases)
				try {
					res.add(Class.forName(getPaqueteImplementaciones() + "." + s));
				} catch (ClassNotFoundException e) {
				}
		}
		return res;
	}

	/**
	 * Debe devolver el paquete donde se encuentran las implementaciones
	 * concretas de las clases generadas por el framework
	 * 
	 * @return String paqueteImplementacionesREST
	 */
	public String getPaqueteImplementaciones() {
		return ServiceREST.getPaqueteImplementaciones();
	}

}
