package fiap.startupOne.model;

import java.time.LocalDate;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@Validated
@SequenceGenerator(name = "acao", sequenceName = "SQ_ACAO", allocationSize = 1)
public class Acao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acao")
	private int codigo;
	
	@ManyToOne
	@Nullable
	private Usuario usuario;
	
	@ManyToOne
	@Nonnull
	private Usuario responsavel;
	
	@Nonnull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate emissao;
	
	@Nonnull
	private int quantidade;
	
	@ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
	@Nonnull
	private Objetivo objetivo;
	
	@Nullable
	private String descricao;
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
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
	
	public void setEmissao(LocalDate emissao) {
		this.emissao = emissao;
	}
	
	public LocalDate getEmissao() {
		return emissao;
	}
	
	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
	
	public Objetivo getObjetivo() {
		return objetivo;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	
	public Usuario getResponsavel() {
		return responsavel;
	}
}

