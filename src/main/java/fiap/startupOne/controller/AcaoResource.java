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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fiap.startupOne.model.Acao;
import fiap.startupOne.repository.AcaoRepository;

@RestController
@RequestMapping("acao")
public class AcaoResource {

	@Autowired
	private AcaoRepository acaoRepository;
	
	@GetMapping
	public List<Acao> listar(){
		return acaoRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	public Acao buscar(@PathVariable int codigo) {
		return acaoRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Acao cadastrar(@RequestBody Acao acao){
		return acaoRepository.save(acao);
	}
	@PutMapping("{id}")
	public Acao atualizar(@RequestBody Acao acao, @PathVariable int id) {
		acao.setCodigo(id);
		return acaoRepository.save(acao);
	}
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		acaoRepository.deleteById(codigo);
	}
}