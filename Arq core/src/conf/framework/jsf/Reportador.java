package conf.framework.jsf;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class Reportador {

	public String error(String error) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		return bundle.getString(error);
	}
}
