package fiap.startupOne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fiap.startupOne.model.Pergunta;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.repository.PerguntaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("pergunta")
@SessionAttributes("usuario")
public class PerguntaResource {

	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@PostMapping("criar")
	public String criaPergunta(@Valid Pergunta pergunta, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		
		int idFormulario = pergunta.getFormulario().getCodigo();
		
		if (result.hasErrors()) {
			return "redirect:/listaPergunta/"+idFormulario;
		}
		
		pergunta.setUsuario((Usuario) model.getAttribute("usuario"));
		
		redirectAttributes.addFlashAttribute("msg","Cadastrado!");
		
		this.cadastrar(pergunta);
		
		return "redirect:/formulario/listaPergunta/"+idFormulario;
	}
	
	@PostMapping("excluir")
	public String excluir(Pergunta pergunta, Model model, RedirectAttributes redirectAttributes) {
		
		pergunta = perguntaRepository.findById(pergunta.getCodigo()).get();
		
		int idFormulario = pergunta.getFormulario().getCodigo();
		
		perguntaRepository.delete(pergunta);
		
		redirectAttributes.addFlashAttribute("msg","Registro removido!");
		
		return "redirect:/formulario/listaPergunta/"+idFormulario;
	}

	
	@GetMapping
	@ResponseBody
	public List<Pergunta> listar(){
		return perguntaRepository.findAll();
	}
	@GetMapping("{codigo}")
	@ResponseBody
	public Pergunta buscar(@PathVariable int codigo) {
		return perguntaRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public Pergunta cadastrar(@RequestBody Pergunta pergunta){
		return perguntaRepository.save(pergunta);
	}
	@PutMapping("{id}")
	@ResponseBody
	public Pergunta atualizar(@RequestBody Pergunta pergunta, @PathVariable int id) {
		pergunta.setCodigo(id);
		return perguntaRepository.save(pergunta);
	}
	@DeleteMapping("{codigo}")
	@ResponseBody
	public void remover(@PathVariable int codigo) {
		perguntaRepository.deleteById(codigo);
	}
}