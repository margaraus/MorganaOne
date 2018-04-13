package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.AlteracaoSalarialDao;
import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.AlteracaoSalarial;
import br.com.potierp.model.Funcionario;

@Stateless(mappedName="AlteracaoSalarialService", name="AlteracaoSalarialService")
@Local(AlteracaoSalarialService.class)
@Interceptors(DAOInterceptor.class)
public class AlteracaoSalarialServiceBean implements AlteracaoSalarialService{
	
	@DAO
	private AlteracaoSalarialDao alteracaoSalarialDao;
	
	@DAO
	private FuncionarioDao funcionarioDao;

	@Override
	public List<AlteracaoSalarial> consultarTodosAlteracaoSalarial()
			throws PotiErpException {
		try {
			Collection<AlteracaoSalarial> responsaveis = alteracaoSalarialDao.getAll();
			return new ArrayList<AlteracaoSalarial>(responsaveis);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"AlteracaoSalarial"}, e);
		}
	}

	@Override
	public AlteracaoSalarial salvar(final AlteracaoSalarial alteracaoSalarial) throws PotiErpException {
		try {
			Funcionario funcionario = funcionarioDao.getByPrimaryKey(alteracaoSalarial.getFuncionario().getId());
			funcionario.setCargo(alteracaoSalarial.getCargoAtual());
			funcionario.setSalario(alteracaoSalarial.getSalarioAtual());
			funcionario = funcionarioDao.salvar(funcionario);
			alteracaoSalarial.setFuncionario(funcionario);
			return alteracaoSalarialDao.salvar(alteracaoSalarial);
		} catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"AlteracaoSalarial"}, e);		
		}
	}

	@Override
	public void excluir(final AlteracaoSalarial alteracaoSalarial) throws PotiErpException {
		try {
			alteracaoSalarialDao.excluir(alteracaoSalarial);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"AlteracaoSalarial"}, e);		
		}
	}

	@Override
	public void excluirLista(final List<AlteracaoSalarial> alteracoesSalarial)
			throws PotiErpException {
		try {
			for (AlteracaoSalarial alteracaoSalarial : alteracoesSalarial) {
				alteracaoSalarialDao.excluir(alteracaoSalarial);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"AlteracaoSalarial"}, e);		
		}
	}
}
