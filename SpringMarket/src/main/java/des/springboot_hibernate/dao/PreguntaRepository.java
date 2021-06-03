package des.springboot_hibernate.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import des.springboot_hibernate.entidades.Pregunta;

@Repository
public interface PreguntaRepository extends PagingAndSortingRepository<Pregunta, Long> {

}
