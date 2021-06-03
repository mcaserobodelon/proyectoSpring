package des.springboot_hibernate.servicios;

import des.springboot_hibernate.entidades.Pregunta;
import des.springboot_hibernate.entidades.PreguntaDTO;

public interface PreguntaServicio {
	
	public PreguntaDTO crearGuardarPregunta(String pregunta, long idCliente, long idLbro);

	public Pregunta obtenerPregunta(Long id);

	public void borrarPregunta(long idPregunta);

}
