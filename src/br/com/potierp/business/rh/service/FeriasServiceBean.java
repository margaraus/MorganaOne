package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.FeriasDao;
import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.dao.SituacaoFuncionarioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Ferias;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.SituacaoFuncionario;

/**
 * @author Andrey Oliveira
 */
@Stateless(mappedName="FeriasService", name="FeriasService")
@Local(FeriasService.class)
@Interceptors(DAOInterceptor.class)
public class FeriasServiceBean implements FeriasService {
	
	@DAO
	private FeriasDao feriasDao;
	
	@DAO
	private FuncionarioDao funcionarioDao;
	
	@DAO
	private SituacaoFuncionarioDao situacaoFuncionarioDao;

	@Override
	public List<Ferias> consultarTodasFerias() throws PotiErpException {
		try {
			Collection<Ferias> ferias = feriasDao.getAll();
			return new ArrayList<Ferias>(ferias);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Ferias"}, e);
		}
	}

	@Override
	public Ferias salvar(Ferias ferias) throws PotiErpException {
		try {
			SituacaoFuncionario situacaoFuncionario = situacaoFuncionarioDao.getByPrimaryKey(ferias.getFuncionario().getSituacaoFuncionario().getId());
			Funcionario funcionario = funcionarioDao.getByPrimaryKey(ferias.getFuncionario().getId());
			funcionario.setSituacaoFuncionario(situacaoFuncionario);
			ferias.setFuncionario(funcionario);
			return feriasDao.salvar(ferias);
		} catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Ferias"}, e);
		}
	}

	@Override
	public void excluir(Ferias ferias) throws PotiErpException {
		try {
			feriasDao.excluir(ferias);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Ferias"}, e);
		}
		
	}

	@Override
	public void excluirLista(List<Ferias> listFerias) throws PotiErpException {
		try {
			for(Ferias ferias : listFerias) {
				feriasDao.excluir(ferias);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Ferias"}, e);
		}
		
	}

	@Override
	public List<Ferias> buscarPorFuncionario(Long idFuncionario)
			throws PotiErpException {
		try {
			return feriasDao.getPorFuncionario(idFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Ferias", "idFuncionario"}, e);
		}
	}
	
	
}
