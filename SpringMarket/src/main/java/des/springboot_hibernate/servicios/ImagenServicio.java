package des.springboot_hibernate.servicios;

import org.springframework.web.multipart.MultipartFile;

import des.springboot_hibernate.entidades.Imagen;

public interface ImagenServicio {
	
	public int guardarImagen(Imagen img);
	
	public Imagen obtenerImagen(Long id);
	
	public Boolean actualizarImagen(long idProfesor, MultipartFile file);

}
