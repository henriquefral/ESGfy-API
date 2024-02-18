package fiap.startupOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Formulario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Integer>{

}