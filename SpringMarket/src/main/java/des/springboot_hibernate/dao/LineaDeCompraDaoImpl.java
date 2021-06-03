package des.springboot_hibernate.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate.entidades.LineaDeCompra;

@Repository
@Component("LineaDeCompraDao")
public class LineaDeCompraDaoImpl extends DaoGenericoImpl<LineaDeCompra> implements LineaDeCompraDao {

	

}