package fiap.startupOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiap.startupOne.model.RankPilarESG;
import fiap.startupOne.model.Fornecedor;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.model.PilarESG;

@Repository
public interface RankPilarESGRepository extends JpaRepository<RankPilarESG, Integer>{

	RankPilarESG findByUsuarioAndFornecedorAndPilarESG(Usuario usuario, Fornecedor fornecedor, PilarESG pilarESG);
	
}