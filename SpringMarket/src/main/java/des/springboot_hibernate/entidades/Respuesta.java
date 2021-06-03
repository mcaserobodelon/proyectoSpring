package des.springboot_hibernate.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "RESPUESTA")

public class Respuesta implements Serializable {
	
	private static final long serialVersionUID = -7067377195518937621L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESPUESTA")
	private Long idRespuesta;

	@Column(name = "TEXTO_RESPUESTA")
	private String textoRespuesta;

	@Column(name = "FECHA_RESPUESTA")
	private LocalDate fechaRespuesta;
	
	// OneToMany 
		@ManyToOne
		@JoinColumn(name = "ID_CLIENTE")
		private Cliente cliente;

	// OneToMany 
	@ManyToOne
	@JoinColumn(name = "ID_PREGUNTA")
	private Pregunta pregunta;

	
	
	
	public Respuesta() {
		super();
	}
	
	public Respuesta(String textoRespuesta, LocalDate fechaRespuesta, Cliente cliente, Pregunta pregunta) {
		super();
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.cliente = cliente;
		this.pregunta = pregunta;

	}
	
	public Respuesta(String textoRespuesta, LocalDate fechaRespuesta, Pregunta pregunta, Cliente cliente) {
		super();
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.pregunta = pregunta;
		this.cliente = cliente;
	}
	
	public Respuesta(Long idRespuesta, String textoRespuesta, LocalDate fechaRespuesta, Pregunta pregunta,
			Cliente cliente) {
		super();
		this.idRespuesta = idRespuesta;
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.pregunta = pregunta;
		this.cliente = cliente;
	}

	public Long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	public LocalDate getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(LocalDate fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
