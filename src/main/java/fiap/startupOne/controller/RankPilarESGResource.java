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

import fiap.startupOne.model.RankPilarESG;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.repository.RankPilarESGRepository;
import fiap.startupOne.repository.UsuarioRepository;

@RestController
@RequestMapping("rankPilarESG")
public class RankPilarESGResource {

	@Autowired
	private RankPilarESGRepository rankPilarESGRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<RankPilarESG> listar(){
		return rankPilarESGRepository.findAll();
	}
	@GetMapping("{codigo}")
	public RankPilarESG buscar(@PathVariable int codigo) {
		return rankPilarESGRepository.findById(codigo).get();
	}
	@GetMapping("usuario/{codigoUsuario}")
	public List<RankPilarESG> buscarPorUsuario(@PathVariable int codigoUsuario) {
		
		Usuario usuario = usuarioRepository.findById(codigoUsuario).get();
		
		return rankPilarESGRepository.findByUsuarioOrderByPontosDesc(usuario);
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public RankPilarESG cadastrar(@RequestBody RankPilarESG rankPilarESG){
		return rankPilarESGRepository.save(rankPilarESG);
	}
	@PutMapping("{id}")
	public RankPilarESG atualizar(@RequestBody RankPilarESG rankPilarESG, @PathVariable int id) {
		rankPilarESG.setCodigo(id);
		return rankPilarESGRepository.save(rankPilarESG);
	}
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		rankPilarESGRepository.deleteById(codigo);
	}
}