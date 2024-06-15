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

@Entity
@Validated
@SequenceGenerator(name = "rankPilarESG", sequenceName = "SQ_RANKPILARESG", allocationSize = 1)
public class RankPilarESG {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rankPilarESG")
	private int codigo;
	
	@ManyToOne
	@Nonnull
	private PilarESG pilarESG;
	
	private int pontos;
	
	@ManyToOne
	@Nullable
	private Usuario usuario;
	
	@ManyToOne
	@Nullable
	private Fornecedor fornecedor;
		
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
		
	public int getCodigo() {
		return codigo;
	}
	
	public void setPilarESG(PilarESG pilarESG) {
		this.pilarESG = pilarESG;
	}
	
	public PilarESG getPilarESG() {
		return pilarESG;
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
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void somaPontos(int pontos) {
		this.pontos += pontos;
	}
	
	public void removePontos(int pontos) {
		this.pontos -= pontos;
	}
}
