package conf.framework.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import conf.core.Service;
import conf.generadores.rest.GeneradorREST;
import conf.util.BusinessException;
import conf.util.Lector;

public abstract class Application extends javax.ws.rs.core.Application {

	protected Set<Class<?>> res;

	@Override
	public Set<Class<?>> getClasses() {
		if (res == null) {
			res = new HashSet<>();
			List<String> clases;
			try {
				clases = Lector.cargarDirectorio(getRutaImplementaciones()).stream()
						.map(x -> x.substring(0, x.lastIndexOf("."))).collect(Collectors.toList());
				for (String s : clases)
					try {
						res.add(Class.forName(getPaqueteImplementaciones() + "." + s));
						Service.log("Registrada la clase: " + s);
					} catch (ClassNotFoundException e) {
					}
			} catch (BusinessException e) {
				Service.logExcepction("Error al acceder a las clases REST generadas", e);
			}
		}
		return res;
	}

	protected String getPaqueteImplementaciones() {
		return GeneradorREST.getPaqueteImplementaciones().replace("/", ".").replace("src.", "");
	}

	protected String getRutaImplementaciones() {
		return GeneradorREST.getPaqueteImplementaciones();
	}
}
