
package gui;

import java.util.List;

import business.BusinessImple;
import conf.core.Service;
import conf.util.BusinessException;
import model.Libro;
import persistence.PersistenceImple;

/**
 * Ejemplo básico
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class App {

	public static void main(String[] args) throws BusinessException {
		// inicia el framework
		Service s = new Service();
		s.start(new BusinessImple(), new PersistenceImple());
		// comprueba que todo se inicia correctamente
		s.get().persistence().testComunicaBusiness();
		s.get().business().testComunicaPresentacion();

		// app en si
		BusinessImple b = (BusinessImple) s.get().business();

		System.out.println("Listado de libros");
		List<Libro> libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);

		System.out.println("Añadiendo libro D");
		b.añadirLibro("D");

		System.out.println("Listado de libros");
		libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);

		System.out.println("Borrando libro D");
		b.borrarLibro("D");

		System.out.println("Listado de libros");
		libros = b.listaLibros();
		for (Libro l : libros)
			System.out.println(l);

	}
}
