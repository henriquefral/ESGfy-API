package fiap.startupOne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Pergunta;
import fiap.startupOne.model.Formulario;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Integer>{
	
	List<Pergunta> findByFormulario(Formulario formulario);
}
