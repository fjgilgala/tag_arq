package persistence;

import java.util.List;

import conf.core.Persistence;
import conf.core.Service;
import conf.util.BusinessException;
import model.Libro;

public class PersistenceImpl extends Persistence implements LibroDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Libro> getLibros() throws BusinessException {
		return Service.JpaManager().createNamedQuery("Libro.findAll").getResultList();
	}

	@Override
	public void save(Libro l) throws BusinessException {
		Service.JpaManager().persist(l);
	}

	@Override
	public void update(Libro l) throws BusinessException {
	}

	@Override
	public void delete(Long id) throws BusinessException {
	}

	@Override
	public Libro findById(Long id) throws BusinessException {
		return null;
	}

}
