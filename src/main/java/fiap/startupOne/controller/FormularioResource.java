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

import fiap.startupOne.model.Formulario;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.model.Pergunta;
import fiap.startupOne.model.Resposta;
import fiap.startupOne.repository.FormularioRepository;
import fiap.startupOne.repository.PilarESGRepository;
import fiap.startupOne.repository.RespostaRepository;
import fiap.startupOne.repository.PerguntaRepository;
import fiap.startupOne.util.RespostaCreationDto;
import jakarta.validation.Valid;

@Controller
@RequestMapping("formulario")
@SessionAttributes({"usuario", "formulario", "pergunta"})
public class FormularioResource {

	@Autowired
	private FormularioRepository formularioRepository;
	
	@Autowired
	private PilarESGRepository pilarESGRepository;
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@GetMapping("responder/{codigo}")
	public String redirecionaReposta(@PathVariable int codigo, Formulario formulario, Resposta resposta, Model model, RedirectAttributes redirectAttributes) {
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
				
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		RespostaCreationDto respostasForm = new RespostaCreationDto();
		
		Resposta respostaTemp; 
		
		formulario = formularioRepository.findById(codigo).get();
		List<Pergunta> perguntas = perguntaRepository.findByFormulario(formulario);
		
	    for (int i = 0; i < perguntas.size(); i++) {
	    	
	    	respostaTemp = respostaRepository.findByPergunta(perguntas.get(i));
	    	
	    	if ( respostaTemp == null || respostaTemp.getCodigo() == 0 ) {
	    		
	    		respostaTemp = new Resposta();
	    	}
	    		    		
	    	respostasForm.adicionaResposta(respostaTemp);
	    	
	    }
		
		model.addAttribute("formulario"    , formulario);
		model.addAttribute("perguntas"     , perguntas);
		model.addAttribute("respostasForm" , respostasForm);
		
		return "respostaPage/index.html";
	}
	
	@GetMapping("listaPergunta/{codigo}")
	public String redirecionaPergunta(@PathVariable int codigo, Formulario formulario, Model model, RedirectAttributes redirectAttributes) {
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		formulario = formularioRepository.findById(codigo).get();
		
		model.addAttribute("formulario", formulario);
		
		return "redirect:/formulario/listaPergunta";
	}
	
	@GetMapping("listaPergunta")
	public String telaAcoes(Formulario formulario, Pergunta pergunta, Model model, RedirectAttributes redirectAttributes) {
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		formulario = (Formulario) model.getAttribute("formulario");
		
		model.addAttribute("formulario", formulario);
		model.addAttribute("pergunta"  , pergunta);
		model.addAttribute("perguntas" , perguntaRepository.findByFormulario(formulario));
		
		return "perguntaPage/index";
	}	
	@GetMapping("alterar/{codigo}")
	public String telaAlteracao(@PathVariable int codigo, Formulario formulario, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		formulario = formularioRepository.findById(codigo).get();
		
		model.addAttribute("formulario", formulario);
		
		model.addAttribute("pilaresESG", pilarESGRepository.findAll());
		
		return "formularioPage/alteracao";
	}
	
	@PostMapping("alterar")
	public String alterar(@Valid Formulario formulario, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "formularioPage/registro";
		}

		formularioRepository.save(formulario);
		
		redirectAttributes.addFlashAttribute("msg","Registro alterado!");
		
		return "redirect:/usuario/listaFormulario";
	}
		
	@GetMapping("excluir/{codigo}")
	public String telaExclusao(@PathVariable int codigo, Formulario formulario, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		formulario = formularioRepository.findById(codigo).get();
		
		model.addAttribute("formulario", formulario);
		
		model.addAttribute("pilaresESG", pilarESGRepository.findAll());
		
		return "formularioPage/exclusao";
	}
	
	@PostMapping("excluir")
	public String excluir(Formulario formulario, Model model, RedirectAttributes redirectAttributes) {

		formularioRepository.delete(formulario);
		
		redirectAttributes.addFlashAttribute("msg","Registro removido!");
		
		return "redirect:/usuario/listaFormulario";
	}

	
	@GetMapping("criar")
	public String telaCadastro(Formulario formulario, Model model, RedirectAttributes redirectAttributes) {
				
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		model.addAttribute("formulario", new Formulario());
		model.addAttribute("pilaresESG", pilarESGRepository.findByUsuario(usuario));
		
		return "formularioPage/registro";
	}
	
	@PostMapping("criar")
	public String criaFormulario(@Valid Formulario formulario, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {
			return "formularioPage/registro";
		}
		
		formulario.setUsuario((Usuario) model.getAttribute("usuario"));
		
		redirectAttributes.addFlashAttribute("msg","Cadastrado!");
		
		this.cadastrar(formulario);
		
		return "redirect:/usuario/listaFormulario";
	}
	
	@GetMapping
	@ResponseBody
	public List<Formulario> listar(){
		return formularioRepository.findAll();
	}
	@GetMapping("{codigo}")
	@ResponseBody
	public Formulario buscar(@PathVariable int codigo) {
		return formularioRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public Formulario cadastrar(@RequestBody Formulario formulario){
		return formularioRepository.save(formulario);
	}
	@PutMapping("{id}")
	@ResponseBody
	public Formulario atualizar(@RequestBody Formulario formulario, @PathVariable int id) {
		formulario.setCodigo(id);
		return formularioRepository.save(formulario);
	}
	@DeleteMapping("{codigo}")
	@ResponseBody
	public void remover(@PathVariable int codigo) {
		formularioRepository.deleteById(codigo);
	}
}