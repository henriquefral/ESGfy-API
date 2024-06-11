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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fiap.startupOne.model.Fornecedor;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.repository.FornecedorRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("fornecedor")
@SessionAttributes("usuario")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@GetMapping("alterar/{codigo}")
	public String telaAlteracao(@PathVariable int codigo, Fornecedor fornecedor, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		fornecedor = fornecedorRepository.findById(codigo).get();
		
		model.addAttribute("fornecedor", fornecedor);
		
		return "fornecedorPage/alteracao";
	}
	
	@PostMapping("alterar")
	public String alterar(@Valid Fornecedor fornecedor, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "fornecedorPage/registro";
		}

		fornecedorRepository.save(fornecedor);
		
		redirectAttributes.addFlashAttribute("msg","Registro alterado!");
		
		return "redirect:/usuario/listaFornecedor";
	}
		
	@GetMapping("excluir/{codigo}")
	public String telaExclusao(@PathVariable int codigo, Fornecedor fornecedor, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		fornecedor = fornecedorRepository.findById(codigo).get();
		
		model.addAttribute("fornecedor", fornecedor);
		
		return "fornecedorPage/exclusao";
	}
	
	@PostMapping("excluir")
	public String excluir(Fornecedor fornecedor, Model model, RedirectAttributes redirectAttributes) {

		fornecedorRepository.delete(fornecedor);
		
		redirectAttributes.addFlashAttribute("msg","Registro removido!");
		
		return "redirect:/usuario/listaFornecedor";
	}

	@GetMapping("criar")
	public String telaCadastro(Fornecedor fornecedor, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		return "fornecedorPage/registro";
	}
	
	@PostMapping("criar")
	public String criaFornecedor(@Valid Fornecedor fornecedor, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {
			return "fornecedorPage/registro";
		}
		
		fornecedor.setUsuario((Usuario) model.getAttribute("usuario"));
		
		redirectAttributes.addFlashAttribute("msg","Cadastrado!");
		
		this.cadastrar(fornecedor);
		
		return "redirect:/usuario/listaFornecedor";
	}

	
	@GetMapping
	@ResponseBody
	public List<Fornecedor> listar(){
		return fornecedorRepository.findAll();
	}
	@GetMapping("{codigo}")
	@ResponseBody
	public Fornecedor buscar(@PathVariable int codigo) {
		return fornecedorRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public Fornecedor cadastrar(@RequestBody Fornecedor fornecedor){
		return fornecedorRepository.save(fornecedor);
	}
	@PutMapping("{id}")
	@ResponseBody
	public Fornecedor atualizar(@RequestBody Fornecedor fornecedor, @PathVariable int id) {
		fornecedor.setCodigo(id);
		return fornecedorRepository.save(fornecedor);
	}
	@DeleteMapping("{codigo}")
	@ResponseBody
	public void remover(@PathVariable int codigo) {
		fornecedorRepository.deleteById(codigo);
	}
	@GetMapping("pesquisa")
	@ResponseBody
	public List<Fornecedor> buscar(@RequestParam(required = false) String cgc) {
		return fornecedorRepository.findByCgc(cgc);
	}
}