package model;

public class Libro {

	private String titulo;

	public Libro(String titulo) {
		super();
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
		return titulo.equals(((Libro)obj).titulo);
	}

	@Override
	public String toString() {
		return "Libro " + titulo;
	}

}
