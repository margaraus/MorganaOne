package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Demissao;
import br.com.potierp.model.TipoDemissao;

public interface DemissaoService {
	
	Demissao salvarDemissao(Demissao demissao)throws PotiErpException;
	
	void excluirDemissao(Demissao demissao)throws PotiErpException;
	
	void excluirListaDemissao(List<Demissao> demissoes)throws PotiErpException;
	
	List<Demissao> consultarDemissao(Demissao demissao)throws PotiErpException;
	
	List<Demissao> consultarTodasDemissoes() throws PotiErpException;
	
	TipoDemissao salvarTipoDemissao(TipoDemissao tipoDemissao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirTipoDemissao(TipoDemissao tipoDemissao)throws PotiErpException;
	
	void excluirListaTipoDemissao(List<TipoDemissao> tiposDemissoes)throws PotiErpException;
	
	List<TipoDemissao> consultarTipoDemissao(TipoDemissao tipoDemissao)throws PotiErpException;
	
	List<TipoDemissao> consultarTodosTiposDemissoes() throws PotiErpException;
}