package fiap.startupOne.util;

import fiap.startupOne.model.Fornecedor;
import fiap.startupOne.model.Usuario;
import fiap.startupOne.model.PilarESG;
import fiap.startupOne.model.RankPilarESG;

import fiap.startupOne.repository.RankPilarESGRepository;

public class RankESGUtil {

	
	public static RankPilarESG getRankESG(RankPilarESGRepository rankPilarESGRepository, Usuario Usuario, Fornecedor fornecedor, PilarESG pilarESG) {
		
		
		RankPilarESG rankPilarESG = null;
		
		rankPilarESG = rankPilarESGRepository.findByUsuarioAndFornecedorAndPilarESG(Usuario, fornecedor, pilarESG);
		
		if ( rankPilarESG == null || rankPilarESG.getCodigo() == 0 ) 
		{
			rankPilarESG = new RankPilarESG();
			rankPilarESG.setFornecedor(fornecedor);
			rankPilarESG.setPilarESG(pilarESG);
			rankPilarESG.setUsuario(Usuario);
			rankPilarESG.setPontos(0);
		}

		return rankPilarESG;
	}
}

