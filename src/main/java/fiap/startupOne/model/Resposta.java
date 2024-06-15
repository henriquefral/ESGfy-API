package fiap.startupOne.model;

import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@Validated
@SequenceGenerator(name = "resposta", sequenceName = "SQ_RESPOSTA", allocationSize = 1)
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resposta")
	private int codigo;
		
	@Nonnull
	@OneToOne
	private Pergunta pergunta;
	
	
	@Nonnull	
	private String resposta;
	
	@Nonnull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate emissao;
	
	@Nullable
	private int pontos;
	
	@Nullable
	private int resultado;
	
	@ManyToOne
	@Nullable
	private Usuario usuario;
	
	@ManyToOne
	private Usuario responsavel;
		
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
	public Pergunta getPergunta() {
		return pergunta;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	public String getResposta() {
		return resposta;
	}
	
	public void setEmissao(LocalDate emissao) {
		this.emissao = emissao;
	}
	
	public LocalDate getEmissao() {
		return emissao;
	}
	
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	
	public int getResultado() {
		return resultado;
	}
}
