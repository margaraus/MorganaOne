package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.SituacaoFuncionario;

public class SituacaoFuncionarioDao extends BaseDao {

	public SituacaoFuncionarioDao() throws DaoException {
		super();
	}
	
	public SituacaoFuncionario getPorCodigo(final SituacaoFuncionario vinculoEmpregaticio) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(SituacaoFuncionario.GET_BY_CODIGO);
			query.setParameter("codigo", vinculoEmpregaticio.getCodigo());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"SITUACAOFUNCIONARIO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return SituacaoFuncionario.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return SituacaoFuncionario.class;
	}
}