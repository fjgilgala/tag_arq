package conf.framework.jsf;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import conf.util.LoggerImpl;

public class Reportador {

	public static String error(String error) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		LoggerImpl.log("Registrando mensaje " + error);
		return bundle.getString(error);
	}
}
