package des.springboot_hibernate.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_hibernate.entidades.Imagen;
import des.springboot_hibernate.entidades.Libro;
import des.springboot_hibernate.servicios.LibroServicio;


@Controller
@RequestMapping(value = "/libro")
public class LibroControlador {
	
	@Autowired
	LibroServicio libroServicio;
	
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ModelAndView listarLibros() {

		ModelAndView mav = new ModelAndView();

		List<Libro> lLibro = libroServicio.listarLibros();

		mav.addObject("libros", lLibro);
		mav.setViewName("libro/lista");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView perfilLibro(@PathVariable("id") long idLibro) {

		ModelAndView mav = new ModelAndView();

		Libro libros = libroServicio.obtenerLibro(idLibro);
		Imagen imagen = libros.getImagen();
		mav.addObject("libro", libros);
		mav.addObject("imagen", imagen);
		mav.setViewName("/libro/perfil");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "borrar/{id}")
	public String borrarLibro(@PathVariable("id") long idModulo) {

		libroServicio.eliminarLibro(idModulo);

		return "redirect:/libro/lista";
	}
	
	
	@GetMapping("/crear")
	public String showForm() {
		return "libro/crear";
	}
	
	@PostMapping("/crear")
	public String crearLibro(HttpServletRequest request) {

		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		Double precio = Double.parseDouble(request.getParameter("precio"));

		Libro l = new Libro();
		l.setTitulo(titulo);
		l.setAutor(autor);
		l.setPrecio(precio);

		Libro libr = libroServicio.crearLibro(l);

		return "redirect:/libro/lista";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "buscar")
	public ModelAndView buscarLibros(@RequestParam("titulo") String titulo) {
		
		ModelAndView mav = new ModelAndView();

		List<Libro> lLibro = libroServicio.buscarLibros(titulo);

		mav.addObject("libros", lLibro);
		mav.addObject("titulo", titulo);
		mav.setViewName("libro/buscar");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
