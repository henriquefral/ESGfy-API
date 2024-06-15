package fiap.startupOne.util;

import java.util.List;
import java.util.ArrayList;

import fiap.startupOne.model.Resposta;

public class RespostaCreationDto {
	
	private List<Resposta> respostas;
	
	public RespostaCreationDto() {
		this.respostas = new ArrayList<>();
	}
	
	public void adicionaResposta(Resposta resposta) {
		this.respostas.add(resposta);
	}
	
	public void removeResposta(Resposta resposta) {
		this.respostas.remove(resposta);
	}
	
	public List<Resposta> getRespostas() {
		return respostas;
	}
	
	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
}
