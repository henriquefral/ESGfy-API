package fiap.startupOne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fiap.startupOne.model.Fornecedor;
import fiap.startupOne.repository.FornecedorRepository;

@RestController
@RequestMapping("fornecedor")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@GetMapping
	public List<Fornecedor> listar(){
		return fornecedorRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	public Fornecedor buscar(@PathVariable int codigo) {
		return fornecedorRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Fornecedor cadastrar(@RequestBody Fornecedor fornecedor){
		return fornecedorRepository.save(fornecedor);
	}
	@PutMapping("{id}")
	public Fornecedor atualizar(@RequestBody Fornecedor fornecedor, @PathVariable int id) {
		fornecedor.setCodigo(id);
		return fornecedorRepository.save(fornecedor);
	}
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		fornecedorRepository.deleteById(codigo);
	}
	@GetMapping("pesquisa")
	public List<Fornecedor> buscar(@RequestParam(required = false) String cgc) {
		return fornecedorRepository.findByCgc(cgc);
	}

	
}