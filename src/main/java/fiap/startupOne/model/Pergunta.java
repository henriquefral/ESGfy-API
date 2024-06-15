package fiap.startupOne.model;

import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity
@Validated
@SequenceGenerator(name = "pergunta", sequenceName = "SQ_PERGUNTA", allocationSize = 1)
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pergunta")
	private int codigo;
	
	@Nonnull
	private int item;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar um título para a pergunta")
	private String titulo;
	
	@Nullable
	private String subTitulo;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar o tipo da pergunta. Se ela é de múltipla escolha ou digitada")
	private String tipo;
	
	@Nullable
	private int pontos;
	
	@ManyToOne
	@Nullable
	private Usuario usuario;
	
	@ManyToOne
	@Nullable
	private Formulario formulario;
	
	@Nullable
	private String regra;
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}
	
	public String getSubTitulo() {
		return subTitulo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setRegra(String regra) {
		this.regra = regra;
	}
	
	public String getRegra() {
		return regra;
	}
	
	public int getItem() {
		return item;
	}
	
	public void setItem(int item) {
		this.item = item;
	}
	
	public Formulario getFormulario() {
		return formulario;
	}
	
	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}	
}
