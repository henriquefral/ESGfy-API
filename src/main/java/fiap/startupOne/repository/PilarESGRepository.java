package fiap.startupOne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiap.startupOne.model.PilarESG;
import fiap.startupOne.model.Usuario;

@Repository
public interface PilarESGRepository extends JpaRepository<PilarESG, Integer>{
	List<PilarESG> findByUsuario(Usuario usuario);

}