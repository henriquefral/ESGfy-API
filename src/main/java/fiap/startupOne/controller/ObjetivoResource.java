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

import fiap.startupOne.model.Objetivo;
import fiap.startupOne.repository.ObjetivoRepository;

@RestController
@RequestMapping("objetivo")
public class ObjetivoResource {

	@Autowired
	private ObjetivoRepository objetivoRepository;
	
	@GetMapping
	public List<Objetivo> listar(){
		return objetivoRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	public Objetivo buscar(@PathVariable int codigo) {
		return objetivoRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Objetivo cadastrar(@RequestBody Objetivo objetivo){
		return objetivoRepository.save(objetivo);
	}
	@PutMapping("{id}")
	public Objetivo atualizar(@RequestBody Objetivo objetivo, @PathVariable int id) {
		objetivo.setCodigo(id);
		return objetivoRepository.save(objetivo);
	}
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		objetivoRepository.deleteById(codigo);
	}
}