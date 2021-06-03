package des.springboot_hibernate.controladores;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import des.springboot_hibernate.entidades.Carro;
import des.springboot_hibernate.entidades.Cliente;
import des.springboot_hibernate.entidades.Compra;
import des.springboot_hibernate.servicios.ClienteServicio;
import des.springboot_hibernate.servicios.CompraServicio;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteControlador {

	@Autowired
	ClienteServicio clienteServicio;

	@Autowired
	CompraServicio compraServicio;

	@GetMapping("/inciarSesion")
	public String inciarSesion() {
		return "cliente/inciarSesion";
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/perfil")
	@PostMapping("/perfil")
	public String inciarUsuario(HttpServletRequest request, HttpSession httpSession) {
		String direccionemail = request.getParameter("email");
		String contraseña = request.getParameter("password");

		// Comprobacion de que esta logeado, no puede comp

		ModelAndView mav = new ModelAndView();

		Cliente cliente = clienteServicio.logIn(direccionemail, contraseña);

		if (cliente == null) {
			return "redirect:/cliente/inciarSesion";
		} else {

			// Creo la lista de carro ya que ha iniciado sesion
			ArrayList<Carro> arrCarro = null;
			httpSession.setAttribute("arrCarro", arrCarro);

			// Logeado sino, lo está no puede comprar
			Boolean logeado = true;
			httpSession.setAttribute("logeado", logeado);

			// Guardamos en la session el id del cliente para usarlo mas adelante a la hora
			// de pagar
			httpSession.setAttribute("idCliente", cliente.getIdCliente());

			return "redirect:/cliente/perfil/" + cliente.getIdCliente();

		}

	}

	@GetMapping("/perfil/{id}")
	public ModelAndView perfilusuario(@PathVariable("id") Long idUsuario) {

		Cliente cliente = clienteServicio.obtenerUsuarioPorId(idUsuario);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cliente", cliente);
		mav.setViewName("cliente/perfil");
		return mav;
	}

	@GetMapping("/registrar")
	public String showForm() {
		return "cliente/registrar";
	}

	@PostMapping("/registrar")
	public String crearUsuario(HttpServletRequest request) {

		String direccionemail = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String contrasenia = request.getParameter("password");
		String anio = request.getParameter("anio");
		String mes = request.getParameter("mes");
		String dia = request.getParameter("dia");
		// En date hay que pasarle un formato en especifico ej:
		String fnacimiento = anio + "-" + mes + "-" + dia;

		Cliente c = new Cliente();
		c.setEmail(direccionemail);
		c.setNombre(nombre);
		c.setApellidos(apellidos);
		c.setContraseña(contrasenia);
		c.setFechaNacimiento(fnacimiento);

		clienteServicio.crearCliente(c);

		return "redirect:/cliente/inciarSesion";

	}

	@PostMapping("/logout")
	public String destroySessionCliente(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listacompra")
	public ModelAndView listarCompras(HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		
		Long idCliente = (Long) httpSession.getAttribute("idCliente");
		
		// Tomamos los objetos compra asociados a un cliente(que lo obtenemos por su id)
		Cliente p = clienteServicio.obtenerUsuarioPorId(idCliente);
		Set<Compra> lCompra = p.getCompras();

		mav.addObject("compras", lCompra);
		mav.setViewName("cliente/listacompra");
		return mav;
	}

}
