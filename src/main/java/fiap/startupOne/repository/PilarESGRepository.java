package fiap.startupOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.PilarESG;

@Repository
public interface PilarESGRepository extends JpaRepository<PilarESG, Integer>{

}