package des.springboot_hibernate.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_hibernate.entidades.Carro;
import des.springboot_hibernate.entidades.Compra;
import des.springboot_hibernate.entidades.Libro;
import des.springboot_hibernate.entidades.LineaDeCompra;
import des.springboot_hibernate.servicios.ClienteServicio;
import des.springboot_hibernate.servicios.CompraServicio;
import des.springboot_hibernate.servicios.LibroServicio;

@Controller
@RequestMapping(value = "/carro")
public class CarroControlador {

	@Autowired
	LibroServicio libroServicio;

	@Autowired
	ClienteServicio clienteServicio;

	@Autowired
	CompraServicio compraServicio;

	@PostMapping("/aniadir")
	public String aniadirCommpra(HttpServletRequest request, HttpSession httpSession, @RequestParam Long idLibro) {

		Libro libr = libroServicio.obtenerLibro(idLibro);

		Long cantidad = 1l;
		String titulo = libr.getTitulo();

		Carro c = new Carro(idLibro, cantidad, titulo);

		ArrayList<Carro> arrCarro = (ArrayList<Carro>) httpSession.getAttribute("arrCarro");

		if (arrCarro == null) {
			arrCarro = new ArrayList<Carro>();
			arrCarro.add(c);
		} else if (arrCarro.contains(c)) {
			Carro c2 = arrCarro.get(arrCarro.indexOf(c));
			c2.setCantidad(c2.getCantidad() + 1);
			arrCarro.remove(c);
			arrCarro.add(c2);
		} else {
			arrCarro.add(c);
		}

		httpSession.setAttribute("arrCarro", arrCarro);

		// Comprobamos que está logeado

		return "redirect:/carro/lista";

	}

	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ModelAndView listarCarro(HttpServletRequest request, HttpSession httpSession) {

		ArrayList<Carro> arrCarro = (ArrayList<Carro>) httpSession.getAttribute("arrCarro");

		ModelAndView mav = new ModelAndView();

		mav.addObject("arrCarro", arrCarro);
		mav.setViewName("carro/lista");
		return mav;
	}

	@GetMapping("/comprar")
	public String showForm() {
		return "carro/comprar";
	}

	@PostMapping(value = "/comprar")
	public String crearCompra(HttpServletRequest request, HttpSession httpSession) {

		// Tomamos de la sesion el id, que se guardo al hacer sesion
		Long idCliente = (Long) httpSession.getAttribute("idCliente");

		// Creo un objeto compra con los datos que ya tengo y lo guardo en su tabla
		Compra c = new Compra();

		c.setNumtarjeta(Long.parseLong(request.getParameter("numtar")));
		c.setCodseguridad(Long.parseLong(request.getParameter("codseg")));
		c.setDirfacturacion(request.getParameter("dirfac"));
		Set<LineaDeCompra> lineas = new HashSet<>();

		c.setLineas(lineas);

		// Segunda tabla Linea de Carro
		LineaDeCompra l = new LineaDeCompra();
		ArrayList<Carro> arrCarro = (ArrayList<Carro>) httpSession.getAttribute("arrCarro");
		Compra compra = compraServicio.crearCompra(idCliente, arrCarro, c);

		// Vacio el carro ya que al pagar tu compra se vacia
		arrCarro = null;
		httpSession.setAttribute("arrCarro", arrCarro);

		return "redirect:/libro/lista";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView perfilCompra(@PathVariable("id") long idCompra) {

		ModelAndView mav = new ModelAndView();
		Compra compra = compraServicio.obtenerCompra(idCompra);
		Set<LineaDeCompra> lLinea = compra.getLineas();

		mav.addObject("lineas", lLinea);
		mav.setViewName("/carro/perfilCompra");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "borrar/{id}")
	public String borrarCompra(@PathVariable("id") long idCompra) {

		compraServicio.eliminarCompra(idCompra);

		return "redirect:/cliente/listacompra";
	}

}
