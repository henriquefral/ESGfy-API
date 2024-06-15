package fiap.startupOne.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import fiap.startupOne.model.Resposta;
import fiap.startupOne.model.RankPilarESG;
import fiap.startupOne.util.RespostaCreationDto;
import fiap.startupOne.repository.RespostaRepository;
import fiap.startupOne.repository.RankPilarESGRepository;
import fiap.startupOne.repository.FormularioRepository;
import fiap.startupOne.util.RankESGUtil;

@Controller
@RequestMapping("resposta")
@SessionAttributes("formulario")
public class RespostaResource {

	@Autowired
	private RespostaRepository respostaRepository;
	
	@Autowired
	private FormularioRepository formularioRepository;
	
	@Autowired
	private RankPilarESGRepository rankPilarESGRepository;
	
	@PostMapping("responder")
	public String criaFormulario(@ModelAttribute RespostaCreationDto form, Formulario formulario, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {
			return "respostaPage/index";
		}

		for (int i = 0; i < form.getRespostas().size(); i++) {
						
			Resposta antigaResposta = respostaRepository.findById(form.getRespostas().get(i).getCodigo()).orElse(null);
			
			for (int j = 0; j < form.getRespostas().get(i).getPergunta().getFormulario().getPilarESG().size(); j++) {
		    	
		    	RankPilarESG rankPilarESG = RankESGUtil.getRankESG(rankPilarESGRepository, form.getRespostas().get(i).getUsuario(), null, form.getRespostas().get(i).getPergunta().getFormulario().getPilarESG().get(j));
		    	
		    	if ( antigaResposta != null ) {
		    		rankPilarESG.removePontos(antigaResposta.getPontos());
		    	}
		    	rankPilarESG.somaPontos(form.getRespostas().get(i).getPontos());
		    			    	
		    	rankPilarESGRepository.save(rankPilarESG);
		    }
						
			respostaRepository.save(form.getRespostas().get(i));
	    }
		
		formulario.setFinalizacao(LocalDate.now());
		
		formularioRepository.save(formulario);
		
		redirectAttributes.addFlashAttribute("msg","Respondido!");
		
		return "redirect:/usuario/listaFormulario";
	}
	
	@GetMapping
	@ResponseBody
	public List<Resposta> listar(){
		return respostaRepository.findAll();
	}
	@GetMapping("{codigo}")
	@ResponseBody
	public Resposta buscar(@PathVariable int codigo) {
		return respostaRepository.findById(codigo).get();
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@ResponseBody
	public Resposta cadastrar(@RequestBody Resposta resposta){
				
		return respostaRepository.save(resposta);
	}
	@PutMapping("{id}")
	@ResponseBody
	public Resposta atualizar(@RequestBody Resposta resposta, @PathVariable int id) {
		
		resposta.setCodigo(id);
		
		return respostaRepository.save(resposta);
	}
	@DeleteMapping("{codigo}")
	@ResponseBody
	public void remover(@PathVariable int codigo) {
		respostaRepository.deleteById(codigo);
	}
}