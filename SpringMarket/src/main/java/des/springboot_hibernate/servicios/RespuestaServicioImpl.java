package des.springboot_hibernate.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.springboot_hibernate.dao.PreguntaRepository;
import des.springboot_hibernate.dao.RespuestaRepository;
import des.springboot_hibernate.entidades.Cliente;
import des.springboot_hibernate.entidades.Pregunta;
import des.springboot_hibernate.entidades.Respuesta;
import des.springboot_hibernate.entidades.RespuestaDTO;


@Transactional
@Service
public class RespuestaServicioImpl implements RespuestaServicio {
	
	@Autowired
	private RespuestaRepository respuestaRepository;
	
	@Autowired
	private PreguntaRepository preguntaRepository;
	
	@Autowired
	private LibroServicio libroServicio;
	
	@Autowired
	private ClienteServicio clienteServicio;
	
	@Override
	public RespuestaDTO crearGuardarRespuesta(String respuesta, long idCliente, long idPregunta) {

		Cliente cliente = clienteServicio.obtenerUsuarioPorId(idCliente);
		Pregunta pregunta = preguntaRepository.findById(idPregunta).orElse(null);
		LocalDate fechActual = LocalDate.now();
		Respuesta respuestaPersistida = new Respuesta(respuesta, fechActual, cliente, pregunta);
		
		// Aquí persiste el objeto respuesta en bbdd
		Respuesta nueva = respuestaRepository.save(respuestaPersistida);

		// Se crea y devuelve el objeto DTO para usarlo en AJAX
		RespuestaDTO r = new RespuestaDTO();
		r.setTextoRespuesta(respuesta);
		r.setIdRespuesta(nueva.getIdRespuesta());
		r.setFechaRespuesta(fechActual);
		r.setNombreCliente(cliente.getNombre());
		r.setIdCliente(cliente.getIdCliente());
		r.setIdPregunta(pregunta.getIdPregunta());

		return r;
	}
	
	@Override
	public Respuesta obtenerRespuesta(Long id) {
		Respuesta findById = respuestaRepository.findById(id).orElse(null);

		if (findById != null) {
			Respuesta getRespuestaDetails = findById;
			return findById;
		} else {
			return null;
		}
	}
	
	@Override
	public RespuestaDTO modificarRespuesta(Long id, String respuesta) {

		Respuesta res = obtenerRespuesta(id);
		res.setTextoRespuesta(respuesta);

		// Aquí persiste el objeto respuesta en bbdd
		respuestaRepository.save(res);

		// Se crea y devuelve el objeto DTO para usarlo en AJAX
		RespuestaDTO r = new RespuestaDTO();
		r.setTextoRespuesta(res.getTextoRespuesta());
		r.setIdRespuesta(res.getIdRespuesta());
		r.setFechaRespuesta(res.getFechaRespuesta());
		r.setNombreCliente(res.getCliente().getNombre());
		r.setIdCliente(res.getCliente().getIdCliente());
		r.setIdPregunta(res.getPregunta().getIdPregunta());

		return r;
	}
	
	@Override
	public void borrarRespuesta(long idRespuesta) {
		Respuesta p = respuestaRepository.findById(idRespuesta).orElse(null);
		if (p != null) {

			respuestaRepository.deleteById(idRespuesta);
		}

	}

}
