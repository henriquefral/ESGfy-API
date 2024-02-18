package fiap.startupOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Acao;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer>{

}