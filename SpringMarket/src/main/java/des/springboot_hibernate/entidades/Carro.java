package des.springboot_hibernate.entidades;

public class Carro {
	
	Long idLibro;
	Long cantidad;
	String titulo;
	
	public Carro(Long idLibro, Long cantidad, String titulo) {
		super();
		this.idLibro = idLibro;
		this.cantidad = cantidad;
		this.titulo = titulo;
	}
	
	public Carro() {
		
	}
	
	
	public Long getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLibro == null) ? 0 : idLibro.hashCode());
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
		Carro other = (Carro) obj;
		if (idLibro == null) {
			if (other.idLibro != null)
				return false;
		} else if (!idLibro.equals(other.idLibro))
			return false;
		return true;
	}
	
	
	

}
