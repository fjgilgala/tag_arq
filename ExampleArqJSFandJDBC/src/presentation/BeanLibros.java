package presentation;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import business.BusinessImpl;
import business.LibrosService;
import conf.core.Service;
import conf.framework.jsf.Reportador;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImpl;

@ManagedBean(name = "controller")
@SessionScoped
public class BeanLibros implements Serializable {
	private static final long serialVersionUID = 55555L;
	private Libro libro = new Libro();
	private Libro[] libros = null;

	public BeanLibros() throws SQLException, BusinessException {
		Service.start(new BusinessImpl(), new PersistenceImpl());
		iniciaLibro(null);
	}

	public Libro[] getLibros() {
		return (libros);
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibros(Libro[] libros) {
		this.libros = libros;
	}

	public void iniciaLibro(ActionEvent event) {
		libro.setId(null);
		libro.setAutor("autor");
		libro.setTitulo("titulo");
	}

	public String listado() {
		LibrosService service;
		try {
			service = (LibrosService) Service.get().business();
			libros = (Libro[]) service.getLibros().toArray(new Libro[0]);
			return "exito"; // Nos vamos a la vista listado.xhtml
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ERROR", Reportador.error("errorListado"));
			return "error"; // Nos vamos la vista de error
		}
	}

	public String baja() {
		LibrosService service;
		try {
			service = (LibrosService) Service.get().business();
			service.deleteLibro(libro.getId());
			libros = (Libro[]) service.getLibros().toArray(new Libro[0]);
			return "exito"; // Nos vamos a la vista de listado.
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ERROR", Reportador.error("errorBaja"));
			return "error"; // Nos vamos a la vista de error
		}
	}

	public String edit() {
		LibrosService service;
		try {
			service = (LibrosService) Service.get().business();
			libro = service.findById(libro.getId());
			return "exito"; // Nos vamos a la vista de Edición.
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ERROR", Reportador.error("errorEdit"));
			return "error"; // Nos vamos a la vista de error.
		}
	}

	public String salva() {
		LibrosService service;
		try {
			service = (LibrosService) Service.get().business();
			if (libro.getId() == null)
				service.saveLibro(libro);
			else
				service.updateLibro(libro);
			libros = (Libro[]) service.getLibros().toArray(new Libro[0]);
			return "exito"; // Nos vamos a la vista de listado.
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ERROR", Reportador.error("errorSalva"));
			return "error"; // Nos vamos a la vista de error.
		}
	}
}
