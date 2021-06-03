package des.springboot_hibernate.servicios;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import des.springboot_hibernate.dao.ImagenRepository;
import des.springboot_hibernate.dao.LibroDaoImpl;
import des.springboot_hibernate.entidades.Imagen;
import des.springboot_hibernate.entidades.Libro;

@Transactional
@Service
public class ImagenServicioImpl implements ImagenServicio{
	
	@Autowired
	private ImagenRepository imagenRepository;
	
	@Autowired
	private LibroDaoImpl libroDaoImpl;
	
	public int guardarImagen(Imagen img) {
		try {
			imagenRepository.save(img);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public Imagen obtenerImagen(Long id) {
		Imagen findById = imagenRepository.findById(id).orElse(null);
		
		if (findById != null) {
			Imagen getImageDetails = findById;
			return findById;
		} else {
			return null;
		}
	}
	
	public Boolean actualizarImagen(long idLibro, MultipartFile file) {
		
		Libro l = libroDaoImpl.buscar(idLibro);

		if (l == null || file.isEmpty())
			return false;
		try {
			byte[] imagen = file.getBytes();
			if (l.getImagen() != null) {

				Imagen linkedimg = l.getImagen();				
				linkedimg.setImagen(imagen);
				l.setImagen(linkedimg);
				libroDaoImpl.actualizar(l);
				return true;
				

			} else {
				Imagen img = new Imagen("foto", imagen);
				l.addImagen(img);
				imagenRepository.save(img);
//				productoDaoImpl.actualizar(p);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
