package business.actions;

import conf.core.Service;
import conf.framework.jdbc.executor.Action;
import conf.util.BusinessException;
import model.Libro;
import persistence.LibroDao;
import persistence.PersistenceImpl;

public class LibrosAlta extends Action {

	private Libro libro;

	public LibrosAlta(Libro libro) {
		this.libro = libro;
	}

	@Override
	public Object execute() throws BusinessException {
		LibroDao dao = ((PersistenceImpl) Service.get().persistence()).getGatewayLibro();
		try {
			dao.save(libro);
		} catch (BusinessException ex) {
			throw new BusinessException("Libro ya existe " + libro, ex);
		}
		return null;
	}
}
