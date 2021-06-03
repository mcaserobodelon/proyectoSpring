package des.springboot_hibernate.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.springboot_hibernate.dao.PreguntaRepository;
import des.springboot_hibernate.entidades.Cliente;
import des.springboot_hibernate.entidades.Libro;
import des.springboot_hibernate.entidades.Pregunta;
import des.springboot_hibernate.entidades.PreguntaDTO;

@Transactional
@Service
public class PreguntaServicioImpl implements PreguntaServicio{
	
	@Autowired
	private PreguntaRepository preguntaRepository;
	
	@Autowired
	private LibroServicio libroServicio;
	
	@Autowired
	private ClienteServicio clienteServicio;
	
	@Override
	public PreguntaDTO crearGuardarPregunta(String pregunta, long idCliente, long idLibro) {

		Cliente cliente = clienteServicio.obtenerUsuarioPorId(idCliente);
		Libro libro = libroServicio.obtenerLibro(idLibro);
		LocalDate fechActual = LocalDate.now();
		Pregunta preguntaPersistida = new Pregunta(pregunta, fechActual, cliente, libro);
		
		// Aquí persiste el objeto pregunta en bbdd
		Pregunta nueva = preguntaRepository.save(preguntaPersistida);
		
		// Se crea y devuelve el objeto DTO para usarlo en AJAX
		PreguntaDTO p = new PreguntaDTO();
		p.setTextoPregunta(pregunta);
		p.setIdPregunta(nueva.getIdPregunta());
		p.setFechaPregunta(fechActual);
		p.setIdLibro(libro.getIdLibro());
		p.setNombreCliente(cliente.getNombre());
		p.setIdCliente(cliente.getIdCliente());
		p.setRespuestas(null);
		
		return p;
	}
	
	@Override
	public Pregunta obtenerPregunta(Long id) {
		Pregunta findById = preguntaRepository.findById(id).orElse(null);

		if (findById != null) {
			Pregunta getPreguntaDetails = findById;
			return findById;
		} else {
			return null;
		}
	}
	
	@Override
	public void borrarPregunta(long idPregunta) {
		Pregunta p = preguntaRepository.findById(idPregunta).orElse(null);
		if (p != null) {
			preguntaRepository.deleteById(idPregunta);
		}

	}
	
	

}
