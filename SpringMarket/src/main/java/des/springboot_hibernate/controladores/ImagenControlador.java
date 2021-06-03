package des.springboot_hibernate.controladores;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_hibernate.entidades.Carro;
import des.springboot_hibernate.entidades.Cliente;
import des.springboot_hibernate.entidades.Compra;
import des.springboot_hibernate.entidades.Imagen;
import des.springboot_hibernate.entidades.Libro;
import des.springboot_hibernate.servicios.ClienteServicio;
import des.springboot_hibernate.servicios.CompraServicio;
import des.springboot_hibernate.servicios.ImagenServicio;
import des.springboot_hibernate.servicios.LibroServicio;

@Controller
@RequestMapping(value = "/imagenes")
public class ImagenControlador {
	
	@Autowired
	private ImagenServicio imgServicio;
	
	@Autowired
	private LibroServicio libroServicio;
	
	@GetMapping("/cargar/{idLibro}")
	public ModelAndView actualizarFotoLibro (HttpServletRequest request, @PathVariable("idLibro") long idLibro) {
		
		ModelAndView mav = new ModelAndView();
		
		Libro libro = libroServicio.obtenerLibro(idLibro);
		Imagen img = null;
		
		if(libro.getImagen() != null) {
			img = libro.getImagen();
			
		}
		
		mav.addObject("imagen", img);
		mav.addObject("libro", libro);
		mav.setViewName("/imagen/imagen_subir");
		return mav;
		
	}
	
	
	@PostMapping("/cargar/{idLibro}")
	public String subirFotoLibro(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			@PathVariable("idLibro") long idLibro) {
		try {
			byte[] image = file.getBytes();
			Imagen img = new Imagen("foto", image);
			Boolean saveImage = imgServicio.actualizarImagen(idLibro, file);
			if (saveImage) {
				return "redirect:/libro/" + idLibro;
			} else {
				return "redirect:/imagenes/cargar/" + idLibro;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/imagenes/cargar/" + idLibro;
		}
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getImageAsResponseEntity(@PathVariable String id) {

		try {
			Imagen imagesObj = imgServicio.obtenerImagen(Long.parseLong(id));
			byte[] media = imagesObj.getImagen();
			HttpHeaders headers = new HttpHeaders();
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());

			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
			return responseEntity;

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	

	
	

}
