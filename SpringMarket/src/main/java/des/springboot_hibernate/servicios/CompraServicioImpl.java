package des.springboot_hibernate.servicios;

import java.util.ArrayList;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.springboot_hibernate.dao.ClienteDao;
import des.springboot_hibernate.dao.CompraDao;
import des.springboot_hibernate.dao.LineaDeCompraDao;
import des.springboot_hibernate.entidades.Carro;
import des.springboot_hibernate.entidades.Cliente;
import des.springboot_hibernate.entidades.Compra;
import des.springboot_hibernate.entidades.Libro;
import des.springboot_hibernate.entidades.LineaDeCompra;

@Transactional
@Service
public class CompraServicioImpl implements CompraServicio {

	@Autowired
	private CompraDao compraDao;

	@Autowired
	private LineaDeCompraDao lineaDeCompraDao;
	
	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private LibroServicio libroServicio;

	@Override
	public Compra anadirLinea(long idCompra, LineaDeCompra linea) {

		Compra compra = compraDao.buscar(idCompra);
		compra.anadirLineas(linea);

		return compraDao.actualizar(compra);

	}

	@Override
	public Compra crearCompra(long idCliente, ArrayList<Carro> arrCarro, Compra compra) {

		Cliente cliente = clienteDao.buscar(idCliente);
		compra.setIdCliente(cliente);

		for (Carro c : arrCarro) {

			LineaDeCompra linea = new LineaDeCompra();

			linea.setTitulo(c.getTitulo());
			linea.setCantidad(c.getCantidad());

			Libro l = libroServicio.obtenerLibro(c.getIdLibro());
			linea.setLibro(l);

			compra.anadirLineas(linea);
		}

		return compraDao.crear(compra);

	}

	@Override
	public Compra obtenerCompra(long idCompra) {
		return compraDao.buscar(idCompra);
	}

	@Override
	public void eliminarCompra(long idCompra) {

		Compra compra = compraDao.buscar(idCompra);

		Cliente c = compra.getIdCliente();
		c.eliminarCompras(compra);
		
		Set<LineaDeCompra> ldc = compra.getLineas();
		
		// Para poder borrar una compra hay que borras las lineas que tiene asociadas, "vacio"
		
		for (LineaDeCompra linea : ldc) {
			//borramos cada linea de Compra que tiene la compra
			compra.eliminarLineas(linea);
			lineaDeCompraDao.borrar(linea.getIdLinea());
		}
		//al salir del bucle las linea de compra estan borradas
		//por lo que la compra esta vacia y se puede borrar
//		compra.setLineas(ldc);
		
		
		
		//borramos compra sin linea s de compra-
		compraDao.borrar(idCompra);
	}

}
