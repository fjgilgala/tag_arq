package persistence;

import java.util.List;

import conf.util.BusinessException;
import model.Libro;

public interface LibroDao {

	List<Libro> getLibros() throws BusinessException;

	void save(Libro l) throws BusinessException;

	void update(Libro l) throws BusinessException;

	void delete(Long id) throws BusinessException;

	Libro findById(Long id) throws BusinessException;

}