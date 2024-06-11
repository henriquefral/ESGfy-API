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

import fiap.startupOne.model.PilarESG;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.repository.PilarESGRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("pilarESG")
@SessionAttributes("usuario")
public class PilarESGResource {
	
	@Autowired
	private PilarESGRepository pilarESGRepository;

	@GetMapping("alterar/{codigo}")
	public String telaAlteracao(@PathVariable int codigo, PilarESG pilarESG, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		pilarESG = pilarESGRepository.findById(codigo).get();
		
		model.addAttribute("pilarESG", pilarESG);
		
		return "pilarESGPage/alteracao";
	}
	
	@PostMapping("alterar")
	public String alterar(@Valid PilarESG pilarESG, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "pilarESGPage/registro";
		}

		pilarESGRepository.save(pilarESG);
		
		redirectAttributes.addFlashAttribute("msg","Registro alterado!");
		
		return "redirect:/usuario/listaPilarESG";
	}
		
	@GetMapping("excluir/{codigo}")
	public String telaExclusao(@PathVariable int codigo, PilarESG pilarESG, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		pilarESG = pilarESGRepository.findById(codigo).get();
		
		model.addAttribute("pilarESG", pilarESG);
		
		return "pilarESGPage/exclusao";
	}
	
	@PostMapping("excluir")
	public String excluir(PilarESG pilarESG, Model model, RedirectAttributes redirectAttributes) {

		pilarESGRepository.delete(pilarESG);
		
		redirectAttributes.addFlashAttribute("msg","Registro removido!");
		
		return "redirect:/usuario/listaPilarESG";
	}

	@GetMapping("criar")
	public String telaCadastro(PilarESG pilarESG, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		return "pilarESGPage/registro";
	}
	
	@PostMapping("criar")
	public String criaPilarESG(@Valid PilarESG pilarESG, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {
			return "pilarESGPage/registro";
		}
		
		pilarESG.setUsuario((Usuario) model.getAttribute("usuario"));
		
		redirectAttributes.addFlashAttribute("msg","Cadastrado!");
		
		this.cadastrar(pilarESG);
		
		return "redirect:/usuario/listaPilarESG";
	}
	
	@GetMapping
	@ResponseBody
	public List<PilarESG> listar(){
		return pilarESGRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	@ResponseBody
	public PilarESG buscar(@PathVariable int codigo) {
		return pilarESGRepository.findById(codigo).get();
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public PilarESG cadastrar(@RequestBody PilarESG pilarESG){
		return pilarESGRepository.save(pilarESG);
	}
	
	@PutMapping("{id}")
	@ResponseBody
	public PilarESG atualizar(@RequestBody PilarESG pilarESG, @PathVariable int id) {
		pilarESG.setCodigo(id);
		return pilarESGRepository.save(pilarESG);
	}
	
	@DeleteMapping("{codigo}")
	@ResponseBody
	public void remover(@PathVariable int codigo) {
		pilarESGRepository.deleteById(codigo);
	}
}