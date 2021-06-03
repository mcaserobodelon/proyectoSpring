package des.springboot_hibernate.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ROL")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ROL")
	private Integer idRol;
	
	@Column(name = "NOMBRE_ROL")
	private String nombreRol;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	private Set<Cliente> clientes;

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public void anadirCliente(Cliente cliente) {
		this.clientes.add(cliente);
		cliente.getRoles().add(this);
	}
	
	public void deleteUsuario(Cliente cliente) {
		this.clientes.remove(cliente) ;
	}
	
	

}
