package des.springboot_hibernate.servicios;

import des.springboot_hibernate.entidades.Respuesta;
import des.springboot_hibernate.entidades.RespuestaDTO;

public interface RespuestaServicio {
	
	public RespuestaDTO crearGuardarRespuesta(String respuesta, long idCliente, long idPregunta);

	public Respuesta obtenerRespuesta(Long id);

	public RespuestaDTO modificarRespuesta(Long id, String respuesta);

	public void borrarRespuesta(long idRespuesta);

}
