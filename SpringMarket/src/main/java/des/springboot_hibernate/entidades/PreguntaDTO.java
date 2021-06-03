package des.springboot_hibernate.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import des.springboot_hibernate.entidades.Respuesta;



public class PreguntaDTO {
	
	private Long idPregunta;

	private String textoPregunta;

	private LocalDate fechaPregunta;

	private Long idCliente;

	private String nombreCliente;

	private Long idLibro;

	private Set<Respuesta> respuestas = new HashSet<>();
	
	public PreguntaDTO() {
	}
	
	public PreguntaDTO(Long idPregunta, String textoPregunta, LocalDate fechaPregunta, Long idCliente,
			String nombreCliente, Long idLibro, Set<Respuesta> respuestas) {
		super();
		this.idPregunta = idPregunta;
		this.textoPregunta = textoPregunta;
		this.fechaPregunta = fechaPregunta;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.idLibro = idLibro;
		this.respuestas = respuestas;
	}

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getTextoPregunta() {
		return textoPregunta;
	}

	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}

	public LocalDate getFechaPregunta() {
		return fechaPregunta;
	}

	public void setFechaPregunta(LocalDate fechaPregunta) {
		this.fechaPregunta = fechaPregunta;
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

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public Set<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Set<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}


}
