package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Adicional;
import br.com.potierp.model.Beneficio;

public interface ProventoService {
	
	Adicional salvarAdicional(Adicional adicional) throws PotiErpMensagensException, PotiErpException;

	void excluirAdicional(Adicional adicional) throws PotiErpException;

	void excluirListaAdicional(List<Adicional> adicionais) throws PotiErpException;

	List<Adicional> consultarAdicional(Adicional adicional) throws PotiErpException;
	
	List<Adicional> consultarTodosAdicionais() throws PotiErpException;
	
	Beneficio salvarBeneficio(Beneficio beneficio)throws PotiErpMensagensException, PotiErpException;
	
	void excluirBeneficio(Beneficio beneficio)throws PotiErpException;
	
	void excluirListaBeneficio(List<Beneficio> Beneficios) throws PotiErpException;
	
	List<Beneficio> consultarBeneficio(Beneficio beneficio)throws PotiErpException;
	
	List<Beneficio> consultarTodosBeneficios() throws PotiErpException;
}