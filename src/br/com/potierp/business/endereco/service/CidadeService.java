package br.com.potierp.business.endereco.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Estado;

public interface CidadeService {
	
	Cidade salvarCidade(Cidade cidade) throws PotiErpMensagensException, PotiErpException;

	void excluirCidade(Cidade cidade) throws PotiErpException;

	void excluirListaCidade(List<Cidade> cidades) throws PotiErpException;

	List<Cidade> consultarCidade(Cidade cidade) throws PotiErpException;
	
	List<Cidade> consultarTodasCidades() throws PotiErpException;
	
	List<Cidade> consultarPorEstado(Estado estado) throws PotiErpException;
}