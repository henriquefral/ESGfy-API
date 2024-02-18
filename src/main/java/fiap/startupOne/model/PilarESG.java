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
@SequenceGenerator(name = "pilarESG", sequenceName = "SQ_PILARESG", allocationSize = 1)
public class PilarESG {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pilarESG")
	private int codigo;
	
	@Nonnull
	@NotBlank(message = "É preciso dar o nome de indentificação do pilar ESG")
	private String nome;
	
	@Nullable
	private String objetivoSustentavel;
	
	@ManyToOne
	@Nullable
	private Usuario usuario;
			
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setObjetivoSustentavel(String objetivoSustentavel) {
		this.objetivoSustentavel = objetivoSustentavel;
	}
	
	public String getObjetivoSustentavel() {
		return objetivoSustentavel;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}