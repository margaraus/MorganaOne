package br.com.potierp.business.financeiro.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.SolicitacaoPagamento;

/**
 * Contrato para o service de <code>SolicitacaoPagamento</code>.
 * @author felipe
 *
 */
public interface SolicitacaoPagamentoService {
	
	SolicitacaoPagamento salvar(SolicitacaoPagamento solicitacaoPagamento) throws PotiErpMensagensException, PotiErpException;
	
	void excluir(SolicitacaoPagamento solicitacaoPagamento) throws PotiErpMensagensException, PotiErpException;
	
	void excluir(List<SolicitacaoPagamento> listaSolicitacaoPagamento) throws PotiErpMensagensException, PotiErpException;
	
	SolicitacaoPagamento consultarPorId(Long idSolicitacaoPagamento) throws PotiErpException;
	
	List<SolicitacaoPagamento> consultarTodas() throws PotiErpException;

}
