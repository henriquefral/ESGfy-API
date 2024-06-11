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
import jakarta.validation.constraints.Size;

@Entity
@Validated
@SequenceGenerator(name = "fornecedor", sequenceName = "SQ_FORNECEDOR", allocationSize = 1)
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor")
	private int codigo;
	
	@ManyToOne
	@Nullable
	private Usuario usuario; 
	
	@Nonnull
	@NotBlank(message = "É preciso digitar o nome do fornecedor")
	private String nome;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar a razão social do fornecedor")
	private String razaoSocial;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar o cadastro geral de contribuintes do fornecedor")
	@Size(min = 14, max = 18, message = "Favor, ele deve ter 14 dígitos se for um CPF e 18 se for CNPJ")
	private String cgc;
	
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
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setCgc(String cgc) {
		this.cgc = cgc;
	}
	
	public String getCgc() {
		return cgc;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
