package fiap.startupOne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Formulario;
import fiap.startupOne.model.Usuario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Integer>{
	
	List<Formulario> findByUsuario(Usuario usuario);

}