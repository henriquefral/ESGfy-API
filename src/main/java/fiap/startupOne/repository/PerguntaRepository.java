package fiap.startupOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Pergunta;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Integer>{
	
	
}
