package fiap.startupOne.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Acao;
import fiap.startupOne.model.Objetivo;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer>{

	List<Acao> findByObjetivo(Objetivo objetivo);
	
}