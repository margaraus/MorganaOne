package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.dao.ResponsavelDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.Responsavel;

/**
 * @author Doug
 *
 */
@Stateless(mappedName="ResponsavelService", name="ResponsavelService")
@Local(ResponsavelService.class)
@Interceptors(DAOInterceptor.class)
public class ResponsavelServiceBean implements ResponsavelService{
	
	@DAO
	private ResponsavelDao responsavelDao;
	
	@DAO
	private FuncionarioDao funcionarioDao;

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.service.ResponsavelService#consultarTodosResponsaveis()
	 */
	@Override
	public List<Responsavel> consultarTodosResponsaveis()
			throws PotiErpException {
		try {
			Collection<Responsavel> responsaveis = responsavelDao.getAll();
			return new ArrayList<Responsavel>(responsaveis);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Responsavel"}, e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.service.ResponsavelService#salvar(br.com.potierp.model.Responsavel)
	 */
	@Override
	public Responsavel salvar(Responsavel responsavel) throws PotiErpException {
		try {
			List<Responsavel> responsaveis = buscarPorFuncionario(responsavel
					.getFuncionario().getId());
			if(!responsavel.isNew()) {
				responsaveis.remove(responsavel);
			}
			if(!responsaveis.isEmpty()) {
				throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_FUNCIONARIO_JA_E_RESPONSAVEL.getMsg());
			}
			Funcionario funcionario = funcionarioDao.getByPrimaryKey(responsavel.getFuncionario().getId());
			responsavel.setFuncionario(funcionario);
			return responsavelDao.salvar(responsavel);
		} catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Responsavel"}, e);		
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.service.ResponsavelService#excluir(br.com.potierp.model.Responsavel)
	 */
	@Override
	public void excluir(Responsavel responsavel) throws PotiErpException {
		try {
			responsavelDao.excluir(responsavel);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Responsavel"}, e);		
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.service.ResponsavelService#excluirLista(java.util.List)
	 */
	@Override
	public void excluirLista(List<Responsavel> responsaveis)
			throws PotiErpException {
		try {
			for (Responsavel responsavel : responsaveis) {
				responsavelDao.excluir(responsavel);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Responsavel"}, e);		
		}
	}
	
	public List<Responsavel> buscarPorFuncionario(Long idFuncionario) throws PotiErpException {
		try {
			return responsavelDao.getPorFuncionario(idFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Responsavel", "ID"}, e);		
		}
	}

}
