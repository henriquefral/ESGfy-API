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

import fiap.startupOne.model.Pergunta;
import fiap.startupOne.repository.PerguntaRepository;

@RestController
@RequestMapping("pergunta")
public class PerguntaResource {

	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@GetMapping
	public List<Pergunta> listar(){
		return perguntaRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	public Pergunta buscar(@PathVariable int codigo) {
		return perguntaRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Pergunta cadastrar(@RequestBody Pergunta pergunta){
		return perguntaRepository.save(pergunta);
	}
	@PutMapping("{id}")
	public Pergunta atualizar(@RequestBody Pergunta pergunta, @PathVariable int id) {
		pergunta.setCodigo(id);
		return perguntaRepository.save(pergunta);
	}
	
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		perguntaRepository.deleteById(codigo);
	}
}