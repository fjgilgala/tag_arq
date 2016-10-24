package conf.framework.jsf;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "error")
@SessionScoped
public class GestorErroresBean implements Serializable {

	private static final long serialVersionUID = 55556L;
	private String error;

	public String getError() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		error = (String) session.get("ERROR");
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}