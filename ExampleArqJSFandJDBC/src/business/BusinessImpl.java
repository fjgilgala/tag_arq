package business;

import java.util.List;

import business.actions.LibrosAlta;
import business.actions.LibrosBaja;
import business.actions.LibrosBuscar;
import business.actions.LibrosListado;
import business.actions.LibrosUpdate;
import conf.framework.jdbc.core.BusinessJDBCImpl;
import conf.util.BusinessException;
import model.Libro;

public class BusinessImpl extends BusinessJDBCImpl implements LibrosService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Libro> getLibros() throws BusinessException {
		return (List<Libro>) executor.execute(new LibrosListado());
	}

	@Override
	public void saveLibro(Libro Libro) throws BusinessException {
		executor.execute(new LibrosAlta(Libro));
	}

	@Override
	public void updateLibro(Libro Libro) throws BusinessException {
		executor.execute(new LibrosUpdate(Libro));
	}

	@Override
	public Libro findById(Long id) throws BusinessException {
		return (Libro) executor.execute(new LibrosBuscar(id));
	}

	@Override
	public void deleteLibro(Long id) throws BusinessException {
		executor.execute(new LibrosBaja(id));
	}
}
