package persistence;

import java.util.List;

import conf.core.Persistence;
import conf.core.Service;
import conf.util.BusinessException;
import model.Libro;

public class PersistenceImpl extends Persistence implements LibroDao {

	// o bien Service.JpaManager() o Jpa.getManager()

	@SuppressWarnings("unchecked")
	@Override
	public List<Libro> getLibros() throws BusinessException {
		// return Service.JpaManager().createQuery("select l from Libro l").getResultList();
		return Service.JpaManager().createNamedQuery("Libro.findAll").getResultList();
	}

	@Override
	public void save(Libro l) throws BusinessException {
		Service.JpaManager().persist(l);
	}

	@Override
	public void update(Libro l) throws BusinessException {
		Libro aux = findById(l.getId());
		aux.setAutor(l.getAutor());
		aux.setTitulo(l.getTitulo());
	}

	@Override
	public void delete(Long id) throws BusinessException {
		Service.JpaManager().remove(findById(id));
	}

	@Override
	public Libro findById(Long id) throws BusinessException {
		return Service.JpaManager().find(Libro.class, id);
	}

}
