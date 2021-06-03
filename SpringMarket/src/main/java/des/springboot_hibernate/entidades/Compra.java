package des.springboot_hibernate.entidades;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "COMPRA")
public class Compra {
	
	private static final long serialVersionUID = -7929690108419204859L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMPRA")
	private Long idCompra;

	@Column(name = "NUMERO_TARJETA")
	private Long numtarjeta;
	
	@Column(name = "CODIGO_SEGURIDAD")
	private Long codseguridad;
	
	@Column(name = "DIRECCION_FAC")
	private String dirfacturacion;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente idCliente;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "compra", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private Set<LineaDeCompra> lineas = new HashSet<>();



	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Long getNumtarjeta() {
		return numtarjeta;
	}

	public void setNumtarjeta(Long numtarjeta) {
		this.numtarjeta = numtarjeta;
	}

	public Long getCodseguridad() {
		return codseguridad;
	}

	public void setCodseguridad(Long codseguridad) {
		this.codseguridad = codseguridad;
	}

	public String getDirfacturacion() {
		return dirfacturacion;
	}

	public void setDirfacturacion(String dirfacturacion) {
		this.dirfacturacion = dirfacturacion;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<LineaDeCompra> getLineas() {
		return lineas;
	}

	public void setLineas(Set<LineaDeCompra> lineas) {
		this.lineas = lineas;
	}
	
	public boolean anadirLineas(LineaDeCompra linea) {
		linea.setCompra(this);
		return getLineas().add(linea);
	}
	
	public boolean eliminarLineas(LineaDeCompra linea) {
		return getLineas().remove(linea);
	}
	
	
	
	
}
