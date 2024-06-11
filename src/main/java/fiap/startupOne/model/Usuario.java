package fiap.startupOne.model;

import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Validated
@SequenceGenerator(name = "usuario", sequenceName = "SQ_USUARIO", allocationSize = 1)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
	private int codigo;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar o e-mail da empresa/pessoa")
	private String email;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar a senha da empresa/pessoa")
	private String senha;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar o nome da empresa/pessoa")
	private String nome;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar a razão social ou um nome formal da empresa/pessoa")
	private String razaoSocial;
	
	@Nonnull
	@NotBlank(message = "É preciso digitar o cadastro geral de contribuintes da empresa atual")
	@Size(min = 14, max = 18, message = "Favor, ele deve ter 11 dígitos se for um CPF e 14 se for CNPJ")
	private String cgc;
	
	@Nonnull
	@NotBlank(message = "É preciso informar o tipo do usuário")
	private String tipo;
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
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
	
	public void convertCgc() {
		setCgc(this.cgc.replace(".", ""));
		setCgc(this.cgc.replace("-", ""));
		setCgc(this.cgc.replace("/", ""));
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
}
