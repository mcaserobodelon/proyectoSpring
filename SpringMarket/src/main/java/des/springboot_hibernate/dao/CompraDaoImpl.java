package des.springboot_hibernate.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate.entidades.Compra;


@Repository
@Component("EmailDao")
public class CompraDaoImpl extends DaoGenericoImpl<Compra> implements CompraDao{
	
	

}
