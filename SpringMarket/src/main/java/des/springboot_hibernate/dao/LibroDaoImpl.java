package des.springboot_hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate.entidades.Libro;


@Repository
@Component("LibroDao")
public class LibroDaoImpl extends DaoGenericoImpl<Libro> implements LibroDao {
	
	@Override
	public List<Libro> listarLibros() {
		Query query = this.em.createQuery("FROM Libro");
		List<Libro> lLibro = query.getResultList();

		if (lLibro != null) {
			return lLibro;
		}
		return null;
	}
	
	@Override
	public List<Libro> buscarPorTitulo(String titulo)  {
		Query query = this.em.createQuery("FROM Libro u where u.titulo= :titulo");
		query.setParameter("titulo", "%"+titulo+"%");
		List<Libro> listLibro = query.getResultList();

		if (listLibro != null) {
			return listLibro;
		}
		return null;
	}

}
