package fiap.startupOne.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiap.startupOne.model.Pergunta;
import fiap.startupOne.model.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Integer>{
	Resposta findByPergunta(Pergunta pergunta);
}