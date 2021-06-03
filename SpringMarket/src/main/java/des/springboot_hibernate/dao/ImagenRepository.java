package des.springboot_hibernate.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate.entidades.Imagen;

@Repository
public interface ImagenRepository extends CrudRepository<Imagen, Long>{

}
