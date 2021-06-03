package des.springboot_hibernate.dao;

import java.util.List;
import des.springboot_hibernate.entidades.Libro;

public interface LibroDao extends DaoGenerico<Libro>{
	
	public List<Libro> listarLibros();
	
	public List<Libro> buscarPorTitulo(String titulo);

}
