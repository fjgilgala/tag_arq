package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Libro ejemplo de clase del modelo de dominio de la aplicación
 * 
 * @author Francisco Javier Gil Gala
 *
 */
@Entity
@Table(name = "Libro")
public class Libro {

	@Id
	@GeneratedValue
	private int id;
	private String titulo;

	public Libro() {
	}

	public Libro(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + "]";
	}

}
