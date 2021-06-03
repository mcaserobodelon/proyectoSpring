package des.springboot_hibernate.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.springboot_hibernate.dao.LibroDao;
import des.springboot_hibernate.entidades.Libro;
import des.springboot_hibernate.entidades.LineaDeCompra;


@Transactional
@Service
public class LibroServicioImpl implements LibroServicio {
	
	@Autowired
	private LibroDao libroDao;
	
	@Override
	public List<Libro> listarLibros() {
		return libroDao.listarLibros();
	}
	
	@Override
	public List<Libro> buscarLibros(String titulo) {
		return libroDao.buscarPorTitulo(titulo);
	}
	
	@Override
	public Libro crearLibro (Libro libro) {
		return libroDao.crear(libro);
	}
	
	@Override
	public Libro obtenerLibro(long idLibro) {
		return libroDao.buscar(idLibro);
	}
	
	public void eliminarLibro(long idLibro) {

		libroDao.borrar(idLibro);
	}
	
	@Override
	public Libro anadirLinea (long idLibro, LineaDeCompra linea) {
		
		Libro libro = libroDao.buscar(idLibro);
		libro.anadirLineas(linea);
		
		return libroDao.actualizar(libro);
		
		
	}
	
	


}
