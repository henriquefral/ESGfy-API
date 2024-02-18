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

import fiap.startupOne.model.Usuario;
import fiap.startupOne.repository.UsuarioRepository;

@RestController
@RequestMapping("usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	public Usuario buscar(@PathVariable int codigo) {
		return usuarioRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Usuario cadastrar(@RequestBody Usuario usuario){
		return usuarioRepository.save(usuario);
	}
	@PutMapping("{id}")
	public Usuario atualizar(@RequestBody Usuario usuario, @PathVariable int id) {
		usuario.setCodigo(id);
		return usuarioRepository.save(usuario);
	}
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		usuarioRepository.deleteById(codigo);
	}
}