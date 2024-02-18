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

import fiap.startupOne.model.Formulario;
import fiap.startupOne.repository.FormularioRepository;

@RestController
@RequestMapping("formulario")
public class FormularioResource {

	@Autowired
	private FormularioRepository formularioRepository;
	
	@GetMapping
	public List<Formulario> listar(){
		return formularioRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	public Formulario buscar(@PathVariable int codigo) {
		return formularioRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Formulario cadastrar(@RequestBody Formulario formulario){
		return formularioRepository.save(formulario);
	}
	@PutMapping("{id}")
	public Formulario atualizar(@RequestBody Formulario formulario, @PathVariable int id) {
		formulario.setCodigo(id);
		return formularioRepository.save(formulario);
	}
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		formularioRepository.deleteById(codigo);
	}
}