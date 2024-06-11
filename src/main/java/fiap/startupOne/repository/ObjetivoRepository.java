package fiap.startupOne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Objetivo;
import fiap.startupOne.model.Usuario;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer>{
	
	List<Objetivo> findByUsuario(Usuario usuario);

}