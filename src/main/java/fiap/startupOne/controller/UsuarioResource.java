package fiap.startupOne.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fiap.startupOne.model.Usuario;
import fiap.startupOne.repository.PilarESGRepository;
import fiap.startupOne.repository.ObjetivoRepository;
import fiap.startupOne.repository.UsuarioRepository;
import fiap.startupOne.repository.FornecedorRepository;
import fiap.startupOne.repository.FormularioRepository;

@Controller
@RequestMapping("usuario")
@SessionAttributes("usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ObjetivoRepository objetivoRepository;
	
	@Autowired
	private PilarESGRepository pilarESGRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FormularioRepository formularioRepository;

	@GetMapping("listaFormulario")
	public String montaNavegacaoFormulario(Model model, RedirectAttributes redirectAttributes){
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:login";
		}
		
		model.addAttribute("formularios", formularioRepository.findByUsuario(usuario));
		
		return "formularioPage/index";
	}
	
	@GetMapping("listaFornecedor")
	public String montaNavegacaoFornecedor(Model model, RedirectAttributes redirectAttributes){
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:login";
		}
		
		model.addAttribute("fornecedores", fornecedorRepository.findByUsuario(usuario));
		
		return "fornecedorPage/index";
	}
	
	@GetMapping("listaPilarESG")
	public String montaNavegacaoPilarESG(Model model, RedirectAttributes redirectAttributes){
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:login";
		}
		
		model.addAttribute("pilaresESG", pilarESGRepository.findByUsuario(usuario));
		
		return "pilarESGPage/index";
	}
	
	@GetMapping("listaObjetivo")
	public String montaNavegacaoObjetivo(Model model, RedirectAttributes redirectAttributes){
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:login";
		}
		
		model.addAttribute("objetivos", objetivoRepository.findByUsuario(usuario));
		
		return "objetivoPage/index";
	}
	
	@GetMapping("login")
	public String telaLogin(Usuario usuario, Model model){
		
		return "loginPage/index";
	}
	
	@PostMapping("logar")
	public String logar(Usuario usuario, Model model, RedirectAttributes redirectAttributes){
		
		usuario = this.logar(usuario, model);
		
		if ( usuario == null ) {
			redirectAttributes.addFlashAttribute("msgE","Usuário não foi encontrado!");
			
			return "redirect:login";
		}
		
		model = model.addAttribute("usuario", usuario);
		
		return "redirect:menu";
	}
	
	@GetMapping("menu")
	public String telaObjetivo(Model model, RedirectAttributes redirectAttributes){
		
		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:login";
		}
		
		return "menuPage/index";
	}
	
	@PostMapping("login")
	@ResponseBody
	public Usuario logar(@RequestBody Usuario usuario, Model model){
			
		return usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
	}
	
	@GetMapping("cadastro")
	public String telaCadastro(Usuario usuario, Model model){
		
		return "registerPage/index";
	}
	
	@PostMapping("cadastrar")
	public String criaUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {
			return "registerPage/index";
		}
		redirectAttributes.addFlashAttribute("msg","Cadastrado!");
		
		this.cadastrar(usuario);
		
		return "redirect:login";
	}
	
	@GetMapping
	@ResponseBody
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	@ResponseBody
	public Usuario buscar(@PathVariable int codigo) {
		return usuarioRepository.findById(codigo).get();
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public Usuario cadastrar(@RequestBody Usuario usuario){
		
		return usuarioRepository.save(usuario);
	}
	
	@PutMapping("{id}")
	@ResponseBody
	public Usuario atualizar(@RequestBody Usuario usuario, @PathVariable int id) {
		usuario.setCodigo(id);
		return usuarioRepository.save(usuario);
	}
	
	@DeleteMapping("{codigo}")
	@ResponseBody
	public void remover(@PathVariable int codigo) {
		usuarioRepository.deleteById(codigo);
	}
}