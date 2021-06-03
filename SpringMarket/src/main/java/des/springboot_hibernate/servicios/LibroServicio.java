package des.springboot_hibernate.servicios;

import java.util.List;

import des.springboot_hibernate.entidades.Libro;
import des.springboot_hibernate.entidades.LineaDeCompra;


public interface LibroServicio {
	
	public List<Libro> listarLibros();
	
	public List<Libro> buscarLibros(String titulo );
	
	public Libro crearLibro (Libro libro);
	
	public Libro obtenerLibro(long idLibro);
	
	public void eliminarLibro(long idLibro);
	
	public Libro anadirLinea (long idLibro, LineaDeCompra linea);

	
	
}
