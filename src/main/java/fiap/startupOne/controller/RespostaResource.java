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

import fiap.startupOne.model.Resposta;
import fiap.startupOne.repository.RespostaRepository;

@RestController
@RequestMapping("resposta")
public class RespostaResource {

	@Autowired
	private RespostaRepository respostaRepository;
	
	@GetMapping
	public List<Resposta> listar(){
		return respostaRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	public Resposta buscar(@PathVariable int codigo) {
		return respostaRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Resposta cadastrar(@RequestBody Resposta resposta){
		return respostaRepository.save(resposta);
	}
	@PutMapping("{id}")
	public Resposta atualizar(@RequestBody Resposta resposta, @PathVariable int id) {
		resposta.setCodigo(id);
		return respostaRepository.save(resposta);
	}
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		respostaRepository.deleteById(codigo);
	}
}