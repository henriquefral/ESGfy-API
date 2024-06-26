package fiap.startupOne.model;

import java.time.LocalDate;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Validated
@SequenceGenerator(name = "objetivo", sequenceName = "SQ_OBJETIVO", allocationSize = 1)
public class Objetivo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objetivo")
	private int codigo;

	@ManyToOne
	@Nullable
	private Usuario usuario;
	
	@ManyToOne
	@Nonnull
	private Usuario responsavel;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar o nome do objetivo")
	private String nome;
	
	@Nonnull
	@NotBlank(message = "É preciso informar uma descrição do objetivo")
	private String descricao;
	
	@Nonnull
	@Min(value = 1, message = "É preciso informar uma pontuação para o objetivo")
	private int pontos;
	
	@Nonnull
	@Min(value = 1, message = "É preciso quantas vezes este objetivo precisa ser cumprido")
	private int quantidade;
	
	@Nonnull
	@ColumnDefault("0")
	private int quantidadeEntregue;
	
	@Nonnull
	@NotBlank(message = "É preciso informar a unidade de medida para a quantidade. Ex: Horas, dias, KG, Ml...")
	private String unidadeDeMedida;
	
	@ManyToMany
	@Nonnull()
	private List<PilarESG> pilarESG;
	
	@OneToMany(mappedBy="objetivo", cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Acao> acao;
	
	@Nonnull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate emissao;
	
	@Nullable
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate finalizacao;
	
	@Nonnull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate previsaoTermino;
		
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	
	public Usuario getResponsavel() {
		return responsavel;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setPilarESG(List<PilarESG> pilarESG) {
		this.pilarESG = pilarESG;
	}
	
	public List<PilarESG> getPilarESG() {
		return pilarESG;
	}
	
	public LocalDate getEmissao() {
		return emissao;
	}
	
	public void setEmissao(LocalDate emissao) {
		this.emissao = emissao;
	}
	
	public LocalDate getFinalizacao() {
		return finalizacao;
	}
	
	public void setFinalizacao(LocalDate finalizacao) {
		this.finalizacao = finalizacao;
	}
	
	public void setUnidadeDeMedida(String unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}
	
	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}
	
	public void setPrevisaoTermino(LocalDate previsaoTermino) {
		this.previsaoTermino = previsaoTermino;
	}
	
	public LocalDate getPrevisaoTermino() {
		return previsaoTermino;
	}
		
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	
	public void setQuantidadeEntregue(int quantidadeEntregue) {
		this.quantidadeEntregue = quantidadeEntregue;
	}
	
	public void somaQuantidadeEntregue(int quantidadeEntregue) {
		this.quantidadeEntregue += quantidadeEntregue;
	}

	public void removeQuantidadeEntregue(int quantidadeEntregue) {
		this.quantidadeEntregue -= quantidadeEntregue;
	}
	public int getQuantidadeEntregue() {
		return quantidadeEntregue;
	}	
}
