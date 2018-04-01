package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.TipoAdmissao;

public interface AdmissaoService {
	
	TipoAdmissao salvarTipoAdmissao(TipoAdmissao tipoAdmissao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirTipoAdmissao(TipoAdmissao tipoAdmissao)throws PotiErpException;
	
	void excluirListaTipoAdmissao(List<TipoAdmissao> tiposAdmissoes)throws PotiErpException;
	
	List<TipoAdmissao> consultarTipoAdmissao(TipoAdmissao tipoAdmissao)throws PotiErpException;
	
	List<TipoAdmissao> consultarTodosTiposAdmissoes() throws PotiErpException;
}