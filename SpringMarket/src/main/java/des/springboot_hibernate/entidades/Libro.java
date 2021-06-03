package des.springboot_hibernate.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table(name = "LIBRO")
public class Libro implements Serializable {

	private static final long serialVersionUID = -8668594760203621162L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIBRO")
	private Long idLibro;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "AUTOR")
	private String autor;

	@Column(name = "PRECIO")
	private Double precio;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaDeCompra> lineas = new HashSet<>();
	
	// OneToOne Imagen
		@OneToOne (mappedBy = "libro", cascade = CascadeType.ALL)
		@PrimaryKeyJoinColumn
		private Imagen imagen;
		
	// OneToMany Pregunta
	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Pregunta> preguntas = new HashSet<>();

	
	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	// Tema de linea

	public Set<LineaDeCompra> getLineas() {
		return lineas;
	}

	public void setLineas(Set<LineaDeCompra> lineas) {
		this.lineas = lineas;
	}
	
	public boolean anadirLineas(LineaDeCompra linea) {
		linea.setLibro(this);
		return getLineas().add(linea);
	}
	
	// Métodos Imagen
		public void addImagen(Imagen img) {
			this.imagen = img;
			img.setLibro(this);
		}

		public void removeImagen(Imagen img) {
			img.setLibro(null);
			this.imagen = null;

		}
		
		//Métodos imagen
		
		public Imagen getImagen() {
			return imagen;
		}

		public void setImagen(Imagen imagen) {
			this.imagen = imagen;
		}
	
	

	public Set<Pregunta> getPreguntas() {
			return preguntas;
		}

		public void setPreguntas(Set<Pregunta> preguntas) {
			this.preguntas = preguntas;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLibro == null) ? 0 : idLibro.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
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
		if (idLibro == null) {
			if (other.idLibro != null)
				return false;
		} else if (!idLibro.equals(other.idLibro))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro{" + "idLibro=" + idLibro + ", titulo='" + titulo + '\'' + ", autor='" + autor + '\'' + ", precio="
				+ precio + '}';
	}

}
