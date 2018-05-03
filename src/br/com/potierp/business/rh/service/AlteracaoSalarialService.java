package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.AlteracaoSalarial;

public interface AlteracaoSalarialService {

	List<AlteracaoSalarial> consultarTodosAlteracaoSalarial() throws PotiErpException;
	
	AlteracaoSalarial salvar(AlteracaoSalarial alteracaoSalarial) throws PotiErpException;
	
	void excluir(AlteracaoSalarial alteracaoSalarial) throws PotiErpException;
	
	void excluirLista(List<AlteracaoSalarial> alteracoesSalarial) throws PotiErpException;
}
