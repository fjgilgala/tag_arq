package business;

import java.util.List;

import conf.util.BusinessException;
import model.Libro;

public interface LibrosService {

	List<Libro> getLibros() throws BusinessException;

	Libro findById(Long id) throws BusinessException;

	void saveLibro(Libro Libro) throws BusinessException;

	void updateLibro(Libro Libro) throws BusinessException;

	void deleteLibro(Long id) throws BusinessException;

}