package des.springboot_hibernate.servicios;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import des.springboot_hibernate.dao.ClienteDao;
import des.springboot_hibernate.entidades.Rol;
import des.springboot_hibernate.entidades.Cliente;



@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	@Transactional()
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente cliente = clienteDao.obtenerUsuarioPorEmail(email);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Rol rol : cliente.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombreRol()));
		}
		return new org.springframework.security.core.userdetails.User(cliente.getEmail(),
				cliente.getContraseña(), grantedAuthorities);
	}

}
