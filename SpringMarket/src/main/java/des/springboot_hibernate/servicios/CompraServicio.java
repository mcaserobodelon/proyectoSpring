package des.springboot_hibernate.servicios;

import java.util.ArrayList;

import des.springboot_hibernate.entidades.Carro;
import des.springboot_hibernate.entidades.Compra;
import des.springboot_hibernate.entidades.LineaDeCompra;

public interface CompraServicio {

	public Compra anadirLinea (long idCompra, LineaDeCompra linea);
	
	public Compra crearCompra  (long idCliente, ArrayList<Carro> arrCarro,Compra compra);
	
	public Compra obtenerCompra(long idCompra);
	
	public void eliminarCompra(long idCompra);
	
}
