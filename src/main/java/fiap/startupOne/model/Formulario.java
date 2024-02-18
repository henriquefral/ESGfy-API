package fiap.startupOne.model;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;

@Entity
@Validated
@SequenceGenerator(name = "formulario", sequenceName = "SQ_FORMULARIO", allocationSize = 1)
public class Formulario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formulario")
	private int codigo;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar documento do formulário")
	private String nome;
	
	@Nullable
	private String descricao;
	
	@ManyToMany
	private List<PilarESG> pilarESG;
	
	@OneToMany
	private List<Pergunta> perguntas;
	
	@ManyToOne
	@Nullable
	private Usuario usuario;
	
	@ManyToOne
	@Nonnull
	@NotBlank(message = "É preciso informar o responsável pelo formulário")
	private Usuario responsavel;
	
	@Nonnull
	@NotBlank(message = "É informar a emissão do objetivo")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate emissao;
	
	@Null
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate finalizacao;

	public void setEmissao(LocalDate emissao) {
		this.emissao = emissao;
	}
	
	public LocalDate getEmissao() {
		return emissao;
	}
	public void setFinalizacao(LocalDate finalizacao) {
		this.finalizacao = finalizacao;
	}
	
	public LocalDate getFinalizacao() {
		return finalizacao;
	}
	
	
	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
	
	public List<Pergunta> getPerguntas() {
		return perguntas;
	}
	
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	
	public Usuario getResponsavel() {
		return responsavel;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setPergunta(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
	
	public List<Pergunta> getPergunta() {
		return perguntas;
	}
	
	public void setPilarESG(List<PilarESG> pilarESG) {
		this.pilarESG = pilarESG;
	}
	
	public List<PilarESG> getPilarESG() {
		return pilarESG;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}

