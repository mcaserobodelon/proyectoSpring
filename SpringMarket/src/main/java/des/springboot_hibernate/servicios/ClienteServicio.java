package des.springboot_hibernate.servicios;

import des.springboot_hibernate.entidades.Cliente;



public interface ClienteServicio {
	
	public Cliente crearCliente (Cliente cliente);
	
	public Cliente logIn (String direccionemail, String contraseña );
	
	public Cliente obtenerUsuarioPorId(Long idUsusario);
	
	public Cliente obtenerUsuarrioPorNombre (String nombre);
	
	public Cliente buscarPorEmail(String emailCliente);
	

	


}
