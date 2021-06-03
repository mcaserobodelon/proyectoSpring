package des.springboot_hibernate.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import des.springboot_hibernate.dao.ClienteDao;
import des.springboot_hibernate.dao.RolRepository;
import des.springboot_hibernate.entidades.Cliente;
import des.springboot_hibernate.entidades.Rol;

@Transactional
@Service
public class ClienteServicioImpl implements ClienteServicio {

	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Cliente crearCliente(Cliente cliente) {
		
		cliente.setContraseña(bCryptPasswordEncoder.encode(cliente.getContraseña()));
		Rol r = rolRepository.findById(2).orElse(null);
		cliente.anadirRol(r);
		return clienteDao.crear(cliente);

	}

	@Override
	public Cliente logIn(String direccionemail, String contraseña) {

		Cliente c = clienteDao.obtenerUsuarioPorEmail(direccionemail);

		if (c != null && c.getContraseña().compareTo(contraseña) == 0) {

			return c;
		} else {
			return null;
		}

	}

	@Override
	public Cliente obtenerUsuarioPorId(Long idUsusario) {
		return clienteDao.buscar(idUsusario);
	}

	@Override
	public Cliente obtenerUsuarrioPorNombre(String nombre) {
		return clienteDao.obtenerUsuarioPorNombre(nombre);
	}

	@Override
	public Cliente buscarPorEmail(String emailCliente) {
		return clienteDao.obtenerUsuarioPorEmail(emailCliente);
	};

}
