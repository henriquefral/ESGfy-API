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

import fiap.startupOne.model.Acao;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.model.Objetivo;
import fiap.startupOne.model.RankPilarESG;
import fiap.startupOne.repository.AcaoRepository;
import fiap.startupOne.repository.ObjetivoRepository;
import fiap.startupOne.repository.RankPilarESGRepository;
import fiap.startupOne.util.RankESGUtil;
import jakarta.validation.Valid;

@Controller
@RequestMapping("acao")
@SessionAttributes({"usuario", "objetivo"})
public class AcaoResource {

	@Autowired
	private AcaoRepository acaoRepository;
	
	@Autowired
	private RankPilarESGRepository rankPilarESGRepository;
	
	@Autowired
	private ObjetivoRepository objetivoRepository;
	
	@GetMapping("alterar/{codigo}")
	public String telaAlteracao(@PathVariable int codigo, Acao acao, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		acao = acaoRepository.findById(codigo).get();
		
		model.addAttribute("acao", acao);
		
		return "acaoPage/alteracao";
	}
	
	@PostMapping("alterar")
	public String alterar(@Valid Acao acao, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "acaoPage/registro";
		}
		
		this.atualizar(acao, acao.getCodigo());
		
		redirectAttributes.addFlashAttribute("msg","Registro alterado!");
		
		return "redirect:/objetivo/listaAcao/"+acao.getObjetivo().getCodigo();
	}
		
	@GetMapping("excluir/{codigo}")
	public String telaExclusao(@PathVariable int codigo, Acao acao, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		acao = acaoRepository.findById(codigo).get();
		
		model.addAttribute("acao", acao);
		
		return "acaoPage/exclusao";
	}
	
	@PostMapping("excluir")
	public String excluir(Acao acao, Model model, RedirectAttributes redirectAttributes) {

		int codigoObjetivo = acao.getObjetivo().getCodigo();
		
		this.remover(codigoObjetivo);
		
		redirectAttributes.addFlashAttribute("msg","Registro removido!");
		
		return "redirect:/objetivo/listaAcao/"+codigoObjetivo;
	}

	
	@GetMapping("criar/{codigoObjetivo}")
	public String direcionaCadastro(@PathVariable int codigoObjetivo, Acao acao, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		model.addAttribute("objetivo", objetivoRepository.findById(codigoObjetivo).orElse(null));
		
		return "redirect:/acao/criar";
	}

	@GetMapping("criar")
	public String telaCadastro(Acao acao, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = (Usuario) model.getAttribute("usuario");
		
		if ( usuario == null || usuario.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","É preciso fazer o login!");
			
			return "redirect:/usuario/login";
		}
		
		Objetivo objetivo = (Objetivo) model.getAttribute("objetivo");
		
		if ( objetivo == null || objetivo.getCodigo() == 0 ) {
			redirectAttributes.addFlashAttribute("msgE","Objetivo inválido!");
			
			return "redirect:/usuario/listaObjetivo";
		}
		
		return "acaoPage/registro";
	}
	
	@PostMapping("criar")
	public String criaAcao(@Valid Acao acao, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {
			return "acaoPage/registro";
		}
		
		acao.setUsuario((Usuario) model.getAttribute("usuario"));
		
		redirectAttributes.addFlashAttribute("msg","Cadastrado!");
		
		this.cadastrar(acao);
		
		return "redirect:/objetivo/listaAcao/"+acao.getObjetivo().getCodigo();
	}

	
	@GetMapping
	@ResponseBody
	public List<Acao> listar(){
		return acaoRepository.findAll();
	}
	
	@GetMapping("{codigo}")
	@ResponseBody
	public Acao buscar(@PathVariable int codigo) {
		return acaoRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public Acao cadastrar(@RequestBody Acao acao){
		
		acao.getObjetivo().somaQuantidadeEntregue(acao.getQuantidade());

		
		if ( acao.getObjetivo().getQuantidadeEntregue() >= acao.getObjetivo().getQuantidade() ) {
			
			for (int i = 0; i < acao.getObjetivo().getPilarESG().size(); i++) {
		    	
		    	RankPilarESG rankPilarESG = RankESGUtil.getRankESG(rankPilarESGRepository, acao.getUsuario(), null, acao.getObjetivo().getPilarESG().get(i));
		    		    	
		    	rankPilarESG.somaPontos(acao.getObjetivo().getPontos());
		    	
		    	rankPilarESGRepository.save(rankPilarESG);
		    }
			
			acao.getObjetivo().setFinalizacao(acao.getEmissao());
		}
		
		objetivoRepository.save(acao.getObjetivo());
		
		return acaoRepository.save(acao);
	}
	@PutMapping("{id}")
	@ResponseBody
	public Acao atualizar(@RequestBody Acao acao, @PathVariable int id) {
		
		Acao antigaAcao = acaoRepository.findById(acao.getCodigo()).get();
		
		acao.setCodigo(id);
						
		acao.getObjetivo().removeQuantidadeEntregue(antigaAcao.getQuantidade());
		
		acao.getObjetivo().somaQuantidadeEntregue(acao.getQuantidade());
		
		if ( acao.getObjetivo().getQuantidadeEntregue() >= acao.getObjetivo().getQuantidade() ) {
			
			for (int i = 0; i < acao.getObjetivo().getPilarESG().size(); i++) {
		    	
		    	RankPilarESG rankPilarESG = RankESGUtil.getRankESG(rankPilarESGRepository, acao.getUsuario(), null, acao.getObjetivo().getPilarESG().get(i));
		    		    	
		    	rankPilarESG.removePontos(antigaAcao.getObjetivo().getPontos());
		    	rankPilarESG.somaPontos(acao.getObjetivo().getPontos());
		    	
		    	rankPilarESGRepository.save(rankPilarESG);
		    }
			
			acao.getObjetivo().setFinalizacao(acao.getEmissao());
		} else {
			
			for (int i = 0; i < acao.getObjetivo().getPilarESG().size(); i++) {
		    	
		    	RankPilarESG rankPilarESG = RankESGUtil.getRankESG(rankPilarESGRepository, acao.getUsuario(), null, acao.getObjetivo().getPilarESG().get(i));
		    		    	
		    	rankPilarESG.removePontos(acao.getObjetivo().getPontos());
		    	
		    	rankPilarESGRepository.save(rankPilarESG);
		    }
			
			acao.getObjetivo().setFinalizacao(null);
		}
		
		objetivoRepository.save(acao.getObjetivo());
		
		return acaoRepository.save(acao);
	}
	@DeleteMapping("{codigo}")
	@ResponseBody
	public void remover(@PathVariable int codigo) {
		
		Acao acao = acaoRepository.findById(codigo).get();
		
		acao.getObjetivo().removeQuantidadeEntregue(acao.getQuantidade());
				
	    for (int i = 0; i < acao.getObjetivo().getPilarESG().size(); i++) {
	    	
	    	RankPilarESG rankPilarESG = RankESGUtil.getRankESG(rankPilarESGRepository, acao.getUsuario(), null, acao.getObjetivo().getPilarESG().get(i));
	    	
	    	rankPilarESG.removePontos(acao.getQuantidade());

	    	rankPilarESGRepository.save(rankPilarESG);
	    }
		
		if ( acao.getObjetivo().getQuantidadeEntregue() < acao.getObjetivo().getQuantidade() ) {
			acao.getObjetivo().setFinalizacao(null);
		} 
		
		objetivoRepository.save(acao.getObjetivo());

		acaoRepository.deleteById(codigo);
	}
}