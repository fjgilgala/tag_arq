package conf.framework.jsf;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Francisco Javier Gil Gala
 *
 */
@ManagedBean(name = "gestor")
@SessionScoped
public class GestorMensajes implements Serializable {

	private static final long serialVersionUID = 55556L;
	private String mensaje;

	public String getMensaje() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		mensaje = (String) session.get("ERROR");
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}