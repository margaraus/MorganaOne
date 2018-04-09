package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.OptimisticLockException;

import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.SituacaoFuncionario;

@Stateless(mappedName="FuncionarioService", name="FuncionarioService")
@Local(FuncionarioService.class)
@Interceptors(DAOInterceptor.class)
public class FuncionarioServiceBean implements FuncionarioService{
	
	@DAO
	private FuncionarioDao funcionarioDao;
	
	@Override
	public Funcionario salvar(final Funcionario funcionario) throws PotiErpMensagensException, PotiErpException {
		try {
			if(!isDuplicado(funcionario)){
				if(funcionario.isNew()){
					return incluirFuncionario(funcionario);
				}else{
					return alterarFuncionario(funcionario);
				}
			}else{
				throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_FUNCIONARIO_COM_ESTE_RE.getMsg());
			}
		} catch (OptimisticLockException ole) {
			throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_OUTRO_USUARIO_ESTA_ALTERANDO_ESTE_FUNCIONARIO.getMsg());
		} catch (DaoException e) {
			throw new PotiErpException(e);
		}
	}

	private boolean isDuplicado(final Funcionario funcionario) throws DaoException {
		Funcionario funcionarioRe = funcionarioDao.getPorRe(funcionario);
		if(funcionarioRe != null && !funcionarioRe.getId().equals(funcionario.getId())){
			return true;
		}
		return false;
	}
	
	private Funcionario incluirFuncionario(final Funcionario funcionario) throws PotiErpException {
		try {
			return funcionarioDao.salvarComFlush(funcionario);
		} catch (Exception de) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Funcionario"}, de);		
		}
	}

	private Funcionario alterarFuncionario(final Funcionario funcionario) throws PotiErpException {
		try {
			return funcionarioDao.salvarComFlush(funcionario);
		} catch(OptimisticLockException ole){
			throw ole;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Funcionario"}, e);		
		}
	}

	@Override
	public void excluir(final Funcionario funcionario) throws PotiErpException {
		try {
			funcionarioDao.excluir(funcionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Funcionario"}, e);
		}
	}
	
	@Override
	public void excluirLista(final List<Funcionario> funcionarios)throws PotiErpException{
		try {
			for(Funcionario funcionario : funcionarios){
				funcionarioDao.excluir(funcionario);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Funcionarios"}, e);
		}
	}

	@Override
	public List<Funcionario> consultar(final Funcionario funcionario) throws PotiErpException {
		try {
			return null;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Funcionario"}, e);
		}
	}
	
	@Override
	public List<Funcionario> consultar(final String prefixo) throws PotiErpException {
		try {
			return funcionarioDao.getPorNome(prefixo);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Funcionario"}, e);
		}
	}

	@Override
	public Funcionario consultar(final Long idFuncionario) throws PotiErpException {
		try {
			return funcionarioDao.getFullPorId(idFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Funcionario"}, e);
		}
	}

	@Override
	public List<Funcionario> consultarTodos() throws PotiErpException {
		try {
			Collection<Funcionario> collectionFuncionario = funcionarioDao.getAll();
			return new ArrayList<Funcionario>(collectionFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Funcionario"}, e);
		}
	}
	
	@Override
	public List<Funcionario> consultarTodos(final int firstRow, final int lastRow) throws PotiErpException {
		try {
			Collection<Funcionario> collectionFuncionario = funcionarioDao.getAll(firstRow, lastRow);
			List<Funcionario> funcionarios = new ArrayList<Funcionario>(collectionFuncionario);
			return funcionarios;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}
	
	@Override
	public List<Funcionario> consultarPesquisa(final Cliente cliente,
			final SituacaoFuncionario situacaoFuncionario) throws PotiErpException {
		try {
			Collection<Funcionario> collectionFuncionario = funcionarioDao.getPorCriteriosPesq(cliente, situacaoFuncionario);
			List<Funcionario> funcionarios = new ArrayList<Funcionario>(collectionFuncionario);
			return funcionarios;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}
	
	@Override
	public Funcionario consultarPorRe(final Funcionario funcionario) throws PotiErpException {
		try {
			return funcionarioDao.getPorRe(funcionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Funcionario"}, e);
		}
	}
	
	@Override
	public List<Funcionario> consultarTodosComNomeRE() throws PotiErpException{
		try {
			Collection<Funcionario> collectionFuncionario = funcionarioDao.getAllComNomeRe();
			List<Funcionario> funcionarios = new ArrayList<Funcionario>(collectionFuncionario);
			return funcionarios;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}
	
	@Override
	public Funcionario consultarPorCalculoValeTransporte(final Long idCalculoValeTransporte) throws PotiErpException{
		try {
			Collection<Funcionario> collectionFuncionario = funcionarioDao.getByCalculoValeTransporte(idCalculoValeTransporte);
			List<Funcionario> funcionarios = new ArrayList<Funcionario>(collectionFuncionario);
			return funcionarios.get(0);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Funcionario"}, e);
		}
	}
	
	@Override
	public Number consultarTotalFuncionarios() throws PotiErpException {
		try{
			return funcionarioDao.getCountAll();
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_TOTAL_ENTIDADE, new Object[]{"Funcionario"}, e);
		}
	}
	
	@Override
	public Number consultarTotalFuncionariosPesquisa(final Cliente cliente,
			final SituacaoFuncionario situacaoFuncionario)
			throws PotiErpException {
		try{
			return funcionarioDao.getCountPorCriterios(cliente, situacaoFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_TOTAL_ENTIDADE, new Object[]{"Funcionario", "Id Cliente"}, e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.service.FuncionarioService#consultarPorDataAdmissao(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Funcionario> consultarPorDataAdmissao(final Date dataInicial,
			final Date dataFinal, final Cidade cidade,
			final Collection<Cliente> clientes) throws PotiErpException {
		try {
			return funcionarioDao.getPorDataAdmissao(dataInicial, dataFinal, cidade, clientes);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Funcionario"}, e);
		}
	}
}