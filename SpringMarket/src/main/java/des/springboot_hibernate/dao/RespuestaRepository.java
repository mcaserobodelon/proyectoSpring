package des.springboot_hibernate.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import des.springboot_hibernate.entidades.Respuesta;

@Repository
public interface RespuestaRepository extends PagingAndSortingRepository <Respuesta, Long> {

}
