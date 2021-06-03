package des.springboot_hibernate.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long idCliente;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDOS")
	private String apellidos;

	@Column(name = "CONTRASEÑA")
	private String contrasenia;	
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "FECHA_NACIMIENTO")
	private String fechaNacimiento;
	
	// OneToMany Compra
	@OneToMany(fetch = FetchType.EAGER, 
			mappedBy = "idCliente", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private Set<Compra> compras = new HashSet<>();
	
	// OneToMany Preguntas 
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Pregunta> preguntas = new HashSet<>();
	
		
	// OneToMany Respuestas 
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Respuesta> respuestas = new HashSet<>();
	
	
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "CLIENTE_ROL", joinColumns = @JoinColumn(name = "ID_CLIENTE"), inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
	private Set<Rol> roles = new HashSet<>();
	
	

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return contrasenia;
	}

	public void setContraseña(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	// Tema de la compra
	
	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public boolean anadirCompras(Compra compra) {
		compra.setIdCliente(this);
		return getCompras().add(compra); 
	}
	
	public boolean eliminarCompras(Compra compra) {
		return getCompras().remove(compra); 
	}
	
	// Tema de los Roles

		public Set<Rol> getRoles() {
			return roles;
		}

		public void setRoles(Set<Rol> roles) {
			this.roles = roles;
		}

		public boolean anadirRol(Rol rol) {
			rol.anadirCliente(this);
			return getRoles().add(rol);
		}

		public void eliminarRol(Rol rol) {
			this.roles.remove(rol);
			rol.getClientes().remove(this);
		}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (contrasenia == null) {
			if (other.contrasenia != null)
				return false;
		} else if (!contrasenia.equals(other.contrasenia))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellidos=" + apellidos + ", contrasenia="
				+ contrasenia + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

	
	

	

}
