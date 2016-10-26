package presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import conf.framework.jsf.GestorMensajes;

@ManagedBean(name = "mensaje")
@SessionScoped
public class BeanMensajes extends GestorMensajes {

	private static final long serialVersionUID = 1L;

}
