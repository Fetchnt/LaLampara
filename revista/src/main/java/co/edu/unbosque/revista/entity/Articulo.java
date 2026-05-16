package co.edu.unbosque.revista.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombreArticulo;
	private String contenido;
	private String genero;
	@Enumerated(EnumType.STRING)
	private TipoRevista tipo;
	private String autor;
	private String comentario;

	public Articulo() {
		super();
	}

	public Articulo(String nombreArticulo, String contenido, String genero, TipoRevista tipo, String autor,
			String comentario) {
		super();
		this.nombreArticulo = nombreArticulo;
		this.contenido = contenido;
		this.genero = genero;
		this.tipo = tipo;
		this.autor = autor;
		this.comentario = comentario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public TipoRevista getTipo() {
		return tipo;
	}

	public void setTipo(TipoRevista tipo) {
		this.tipo = tipo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, comentario, contenido, genero, id, nombreArticulo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(comentario, other.comentario)
				&& Objects.equals(contenido, other.contenido) && Objects.equals(genero, other.genero) && id == other.id
				&& Objects.equals(nombreArticulo, other.nombreArticulo) && tipo == other.tipo;
	}

	

}
