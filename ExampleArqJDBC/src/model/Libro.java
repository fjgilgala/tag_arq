package model;

/**
 * Libro ejemplo de clase del modelo de dominio de la aplicación
 * 
 * @author Francisco Javier Gil Gala
 *
 */
public class Libro {

	private int id;
	private String titulo;

	public Libro(int l, String titulo) {
		super();
		this.id = l;
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public boolean equals(Object obj) {
		return id == ((Libro) obj).id;
	}

	@Override
	public String toString() {
		return "id: " + id + " titulo: " + titulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
