package des.springboot_hibernate.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.springboot_hibernate.dao.LineaDeCompraDao;

@Transactional
@Service
public class LineaDeCompraServicioImpl implements LineaDeCompraServicio{
	
	@Autowired
	private LineaDeCompraDao lineaDeCompraDao;
	
	public void eliminarLinea(long idLinea) {
		
		lineaDeCompraDao.borrar(idLinea);
		
	}

}
