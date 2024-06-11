package fiap.startupOne.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.Fornecedor;
import fiap.startupOne.model.Usuario;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>{
	List<Fornecedor> findByCgc(String cgc);
	
	List<Fornecedor> findByUsuario(Usuario usuario);
}