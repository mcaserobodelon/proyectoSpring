package des.springboot_hibernate.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate.entidades.Cliente;

@Repository
@Component("ClienteDao")
public class ClienteDaoImpl extends DaoGenericoImpl<Cliente> implements ClienteDao {

	// SELECT ID_CLIENTE FROM springboot_hibernate.cliente where EMAIL ='mcasero'
	// AND CONTRASEÑA = '1984';

	@Override
	public Cliente obtenerUsuarioPorEmail(String direccionemail) {
		Query query = this.em.createQuery("FROM Cliente u where u.email= :correo");
		query.setParameter("correo", direccionemail);

		try {
			Cliente cliente = (Cliente) query.getSingleResult();
			return cliente;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Cliente obtenerUsuarioPorNombre(String nombre) {
		Query query = this.em.createQuery("FROM Cliente u where u.nombre= :name");
		query.setParameter("name", nombre);

		try {
			Cliente cliente = (Cliente) query.getSingleResult();
			return cliente;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}


