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

import fiap.startupOne.model.PilarESG;
import fiap.startupOne.repository.PilarESGRepository;

@RestController
@RequestMapping("pilarESG")
public class PilarESGResource {

	@Autowired
	private PilarESGRepository pilarESGRepository;
	
	@GetMapping
	public List<PilarESG> listar(){
		return pilarESGRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	public PilarESG buscar(@PathVariable int codigo) {
		return pilarESGRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public PilarESG cadastrar(@RequestBody PilarESG pilarESG){
		return pilarESGRepository.save(pilarESG);
	}
	@PutMapping("{id}")
	public PilarESG atualizar(@RequestBody PilarESG pilarESG, @PathVariable int id) {
		pilarESG.setCodigo(id);
		return pilarESGRepository.save(pilarESG);
	}
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		pilarESGRepository.deleteById(codigo);
	}
}