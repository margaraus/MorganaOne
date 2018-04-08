package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.TipoCestaBasica;

public interface TipoCestaBasicaService {
	
	TipoCestaBasica salvarTipoCestaBasica(TipoCestaBasica tipoCestaBasica) throws PotiErpMensagensException, PotiErpException;

	void excluirTipoCestaBasica(TipoCestaBasica tipoCestaBasica) throws PotiErpException;

	void excluirListaTipoCestaBasica(List<TipoCestaBasica> tiposCestaBasica) throws PotiErpException;

	List<TipoCestaBasica> consultarTipoCestaBasica(TipoCestaBasica tipoCestaBasica) throws PotiErpException;
	
	List<TipoCestaBasica> consultarTodosTiposCestaBasica() throws PotiErpException;
}