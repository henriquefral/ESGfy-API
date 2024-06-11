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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fiap.startupOne.model.Objetivo;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.repository.ObjetivoRepository;
import fiap.startupOne.repository.AcaoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("objetivo")
@SessionAttributes("usuario")
public class ObjetivoResource {

	@Autowired
	private ObjetivoRepository objetivoRepository;
	
	@Autowired
	private AcaoRepository acaoRepository;
	   
	@GetMapping
	@ResponseBody
	public List<Objetivo> listar(){
		return objetivoRepository.findAll();
	}
	
	@GetMapping("listaAcao/{codigo}")
	public String telaAcoes(@PathVariable int codigo, Objetivo objetivo, Model model, RedirectAttributes redirectAttributes) {
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		objetivo = objetivoRepository.findById(codigo).get();
		
		model.addAttribute("objetivo", objetivo);
		model.addAttribute("acoes"   , acaoRepository.findByObjetivo(objetivo));
		
		return "acaoPage/index";
	}
	
	@GetMapping("alterar/{codigo}")
	public String telaAlteracao(@PathVariable int codigo, Objetivo objetivo, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		objetivo = objetivoRepository.findById(codigo).get();
		
		model.addAttribute("objetivo", objetivo);
		
		return "objetivoPage/alteracao";
	}
	
	@PostMapping("alterar")
	public String alterar(@Valid Objetivo objetivo, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "objetivoPage/registro";
		}

		objetivoRepository.save(objetivo);
		
		redirectAttributes.addFlashAttribute("msg","Registro alterado!");
		
		return "redirect:/usuario/listaObjetivo";
	}
		
	@GetMapping("excluir/{codigo}")
	public String telaExclusao(@PathVariable int codigo, Objetivo objetivo, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		objetivo = objetivoRepository.findById(codigo).get();
		
		model.addAttribute("objetivo", objetivo);
		
		return "objetivoPage/exclusao";
	}
	
	@PostMapping("excluir")
	public String excluir(Objetivo objetivo, Model model, RedirectAttributes redirectAttributes) {

		objetivoRepository.delete(objetivo);
		
		redirectAttributes.addFlashAttribute("msg","Registro removido!");
		
		return "redirect:/usuario/listaObjetivo";
	}

	
	@GetMapping("criar")
	public String telaCadastro(Objetivo objetivo, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		return "objetivoPage/registro";
	}
	
	@PostMapping("criar")
	public String criaObjetivo(@Valid Objetivo objetivo, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {
			return "objetivoPage/registro";
		}
		
		objetivo.setUsuario((Usuario) model.getAttribute("usuario"));
		
		redirectAttributes.addFlashAttribute("msg","Cadastrado!");
		
		this.cadastrar(objetivo);
		
		return "redirect:/usuario/listaObjetivo";
	}
	

	@GetMapping("{codigo}")
	@ResponseBody
	public Objetivo buscar(@PathVariable int codigo) {
		return objetivoRepository.findById(codigo).get();
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public Objetivo cadastrar(@RequestBody Objetivo objetivo){
		return objetivoRepository.save(objetivo);
	}
	
	@PutMapping("{id}")
	@ResponseBody
	public Objetivo atualizar(@RequestBody Objetivo objetivo, @PathVariable int id) {
		objetivo.setCodigo(id);
		return objetivoRepository.save(objetivo);
	}
	
	@DeleteMapping("{codigo}")
	@ResponseBody
	public void remover(@PathVariable int codigo) {
		objetivoRepository.deleteById(codigo);
	}
}