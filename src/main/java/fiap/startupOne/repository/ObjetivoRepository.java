package fiap.startupOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Objetivo;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer>{

}