package des.springboot_hibernate.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "IMAGEN")
public class Imagen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IMAGEN")
	private Long id;
	
	@Column(name = "NOMBRE")
	private String nombre;

	@Lob
	@Column(name = "IMAGEN")
	private byte[] imagen;

	// Relación OneToOne Libro
	@OneToOne
	@JoinColumn(name = "id_libro")
	private Libro libro;
	
	// Getters and Setters
	
	public Imagen() {
		super();
	}

	public Imagen(String name, byte[] image) {
		super();
		this.nombre = name;
		this.imagen = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public int hashCode() {
			return 2020;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        
//		return libro.getIdLibro() != null && libro.getIdLibro().equals(((Imagen) obj).libro.getIdLibro());
				
		return id != null && id.equals(((Imagen) obj).id);
		
	}
	
	
	
	

}





















