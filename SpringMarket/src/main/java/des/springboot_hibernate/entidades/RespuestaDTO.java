package des.springboot_hibernate.entidades;

import java.time.LocalDate;

public class RespuestaDTO {
	
	private Long idRespuesta;

	private Long idPregunta;

	private String textoRespuesta;

	private LocalDate fechaRespuesta;

	private Long idCliente;

	private String nombreCliente;
	
	public RespuestaDTO() {
		super();
	}
	
	public RespuestaDTO(Long idRespuesta, Long idPregunta, String textoRespuesta, LocalDate fechaRespuesta,
			Long idCliente, String nombreCliente) {
		super();
		this.idRespuesta = idRespuesta;
		this.idPregunta = idPregunta;
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
	}
	
	public RespuestaDTO(Long idPregunta, String textoRespuesta, LocalDate fechaRespuesta, Long idCliente,
			String nombreCliente) {
		super();
		this.idPregunta = idPregunta;
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
	}

	public Long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
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

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}
