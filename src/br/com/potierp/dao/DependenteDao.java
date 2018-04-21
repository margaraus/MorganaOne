package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Dependente;

public class DependenteDao extends BaseDao {

	public DependenteDao() throws DaoException {
		super();
	}
	
	public Dependente getPorNomeEFuncionario(final Dependente dependente) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Dependente.GET_BY_NOME_E_FUNCIONARIO);
			query.setParameter("nome", dependente.getNome().trim());
			query.setParameter("codigoRegistro", dependente.getFuncionario().getCodigoRegistro());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"DEPENDENTE"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Dependente.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Dependente.class;
	}
}