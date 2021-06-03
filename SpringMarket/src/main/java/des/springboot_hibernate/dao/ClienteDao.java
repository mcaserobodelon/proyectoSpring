package des.springboot_hibernate.dao;

import des.springboot_hibernate.entidades.Cliente;

public interface ClienteDao extends DaoGenerico<Cliente> {
	
	public Cliente obtenerUsuarioPorEmail(String direccionemail);
	
	public Cliente obtenerUsuarioPorNombre(String nombre);
	
	
	
	
	
}